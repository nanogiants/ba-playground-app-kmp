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

    // dependency versions
    val ktorVersion = "1.2.5"
    val ktorSerializationVersion = "1.2.5"
    val ktorJsonVersion = "1.2.5"
    val sqldelightVersion = "1.2.0"
    val multiplatformSettingsVersion = "0.4"

    // target configurations
    // select iOS target platform depending on the Xcode environment variables
    val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (isDevice)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                // name of the framework
                // used e.g. in swift import SharedPlayground
                baseName = "SharedPlayground"

                // use-case-settings
                export("com.russhwolf:multiplatform-settings:$multiplatformSettingsVersion")
                if (isDevice) {
                    export("com.russhwolf:multiplatform-settings-ios:$multiplatformSettingsVersion")
                } else {
                    export("com.russhwolf:multiplatform-settings-iossim:$multiplatformSettingsVersion")
                }

                // adds type information for generic parameters to Kotlin/Native
                // added in 1.3.40, currently experimental

//                freeCompilerArgs.add("-Xobjc-generics")
            }
        }
    }

    android("android")

    // source set configurations
    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

        // use-case-settings: settings
        implementation("com.russhwolf:multiplatform-settings:$multiplatformSettingsVersion")

        // use-case-nasa: ktor
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-json:$ktorJsonVersion")
        implementation("io.ktor:ktor-client-serialization:$ktorSerializationVersion")
        
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        // use-case-nasa: ktor
        implementation("io.ktor:ktor-client-android:$ktorVersion")
        implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
        implementation("io.ktor:ktor-client-json-jvm:$ktorJsonVersion")
        implementation("io.ktor:ktor-client-serialization-jvm:$ktorSerializationVersion")

        // use-case-notes: sqldelight
        implementation("com.squareup.sqldelight:android-driver:$sqldelightVersion")
    }

    sourceSets["iosMain"].dependencies {
        // use-case-settings: settings
        if (isDevice) {
            api("com.russhwolf:multiplatform-settings-ios:$multiplatformSettingsVersion")
        } else {
            api("com.russhwolf:multiplatform-settings-iossim:$multiplatformSettingsVersion")
        }

        // use-case-nasa: ktor
        implementation("io.ktor:ktor-client-ios:$ktorVersion")
        implementation("io.ktor:ktor-client-core-native:$ktorVersion")
        implementation("io.ktor:ktor-client-json-native:$ktorJsonVersion")
        implementation("io.ktor:ktor-client-serialization-native:$ktorVersion") //?
        if (isDevice) {
            implementation("io.ktor:ktor-client-serialization-iosarm64:$ktorSerializationVersion")
        } else {
            implementation("io.ktor:ktor-client-serialization-iosx64:$ktorSerializationVersion")
        }

        // use-case-notes: sqldelight
        implementation("com.squareup.sqldelight:ios-driver:$sqldelightVersion")
    }
}

sqldelight {
    database("NotesDatabase") {
        // name of generated database class NotesDatabase.kt
        packageName = "de.appcom.kmpplayground" // package name for generated files
        sourceFolders =
            listOf("sqldelight") // root for all paths is src/main, e.g. src/main/sqldelight
        schemaOutputDirectory = file("build/dbs")
    }
    linkSqlite = true
}

android {
    defaultConfig {
        compileSdkVersion(29)
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