import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (isDevice)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "SharedCodeNasa"
            }
        }
    }

    val ktor_version = "1.2.5"
    val coroutines_version = "1.3.2"

    jvm("android")

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

        // ktor
        implementation("io.ktor:ktor-client-core:$ktor_version")
        implementation("io.ktor:ktor-client-json:$ktor_version")
        implementation("io.ktor:ktor-client-serialization:$ktor_version")

        // coroutines
//        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines_version}")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        // ktor
        implementation("io.ktor:ktor-client-android:$ktor_version")
        implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
        implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
        implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")

        // coroutines
//        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines_version}")
    }

    sourceSets["iosMain"].dependencies {
        // ktor
        implementation("io.ktor:ktor-client-ios:$ktor_version")
        implementation("io.ktor:ktor-client-core-native:$ktor_version")
        implementation("io.ktor:ktor-client-json-native:$ktor_version")
        implementation("io.ktor:ktor-client-serialization-native:$ktor_version") //?
        implementation("io.ktor:ktor-client-serialization-iosx64:${ktor_version}") //?

        // coroutines
//        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${coroutines_version}")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS 
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText(
            "#!/bin/bash\n"
                    + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                    + "cd '${rootProject.rootDir}'\n"
                    + "./gradlew \$@\n"
        )
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)