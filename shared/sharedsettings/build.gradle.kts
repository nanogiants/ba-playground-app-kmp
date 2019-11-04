import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    // check if device or simulator
    val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true
    // select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (isDevice)
            ::iosArm64
        else
            ::iosX64

    // create an ios target
    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "SharedSettings"
                export("com.russhwolf:multiplatform-settings:0.3.3")
                if (isDevice) {
                    export("com.russhwolf:multiplatform-settings-ios:0.3.3")
                } else {
                    export("com.russhwolf:multiplatform-settings-iossim:0.3.3")
                }
            }
        }
    }

    // create an android target
    jvm("android")

    // configure source set dependencies for common
    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("com.russhwolf:multiplatform-settings:0.3.3")
    }

    // configure source set dependencies for android
    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
    }

    // configure source set dependencies for ios
    sourceSets["iosMain"].dependencies{
        if (isDevice) {
            api("com.russhwolf:multiplatform-settings-ios:0.3.3")
        } else {
            api("com.russhwolf:multiplatform-settings-iossim:0.3.3")
        }
    }
}

val packForXcodeSettings by tasks.creating(Sync::class) {
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

tasks.getByName("build").dependsOn(packForXcodeSettings)