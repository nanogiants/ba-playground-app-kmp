import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    // kotlin multiplatform plugin
    kotlin("multiplatform")

    // plugins for dependencies: serialization, sqldelighter
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")

    // android plugin and android extensions are necessary for the android target
    id("com.android.library")
    id("kotlin-android-extensions")
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
                baseName = "SharedPlayground"

                // use-case-settings
                export("com.russhwolf:multiplatform-settings:0.3.3")
                if (isDevice) {
                    export("com.russhwolf:multiplatform-settings-ios:0.3.3")
                } else {
                    export("com.russhwolf:multiplatform-settings-iossim:0.3.3")
                }
            }
        }
    }

    // dependency versions
    val versions = mapOf(
        "ktor" to "1.2.5",
        "sqldelight" to "1.2.0"
    )

    // dependency
    val deps = mapOf(
        "ktor_client_core" to "io.ktor:ktor-client-core:${versions["ktor"]}",
        "ktor_client_json" to "io.ktor:ktor-client-json:${versions["ktor"]}",
        "ktor_client_serialization" to "io.ktor:ktor-client-serialization:${versions["ktor"]}",
        "sqldelight" to "io.ktor:ktor-client-json:${versions["ktor"]}",
        "sqldelight" to "io.ktor:ktor-client-json:${versions["ktor"]}",
        "sqldelight" to "io.ktor:ktor-client-json:${versions["ktor"]}"
    )

    android("android")

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

        // use-case-settings: settings
        implementation("com.russhwolf:multiplatform-settings:0.3.3")

        // use-case-nasa: ktor
        implementation("io.ktor:ktor-client-core:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-json:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-serialization:${versions["ktor"]}")

        // coroutines
//        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")// change ??
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        // use-case-nasa: ktor
        implementation("io.ktor:ktor-client-android:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-core-jvm:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-json-jvm:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-serialization-jvm:${versions["ktor"]}")

        // use-case-notes: sqldelight
        implementation("com.squareup.sqldelight:android-driver:${versions["sqldelight"]}")
    }

    sourceSets["iosMain"].dependencies {
        // use-case-settings: settings
        if (isDevice) {
            api("com.russhwolf:multiplatform-settings-ios:0.3.3")
        } else {
            api("com.russhwolf:multiplatform-settings-iossim:0.3.3")
        }

        // use-case-nasa: ktor
        implementation("io.ktor:ktor-client-ios:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-core-native:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-json-native:${versions["ktor"]}")
        implementation("io.ktor:ktor-client-serialization-native:${versions["ktor"]}") //?
        implementation("io.ktor:ktor-client-serialization-iosx64:${versions["ktor"]}") //?

        // use-case-notes: sqldelight
        implementation("com.squareup.sqldelight:ios-driver:${versions["sqldelight"]}")
    }
}

sqldelight {
    database("NotesDatabase") { // name of generated database class NotesDatabase.kt
        packageName = "de.appcom.kmpplayground" // package name for generated files
        sourceFolders = listOf("sqldelight") // root for all pathes is src/main, e.g. src/main/sqldelight
        schemaOutputDirectory = file("build/dbs")
    }
    linkSqlite = true // was false
}

android {
    defaultConfig {
        compileSdkVersion(28)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    // selecting the right configuration for the iOS
    // framework depending on the environment
    // variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    // generate a helpful ./gradlew wrapper with embedded Java path
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