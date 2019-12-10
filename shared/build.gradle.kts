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

    // versions from parent project
    val kotlin_version: String by rootProject.extra

    // dependency versions
    val ktorVersion = "1.2.5"
    val ktorSerializationVersion = "1.2.5"
    val ktorJsonVersion = "1.2.5"
    val sqldelightVersion = "1.2.0"
    val multiplatformSettingsVersion = "0.4"
//    val coroutinesVersion = "1.3.2-1.3.60"// only temporary https://github.com/Kotlin/kotlinx.coroutines/issues/1690
    val coroutinesVersion = "1.3.2"

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
                // name, is used in swift to import the framework
                baseName = "SharedPlayground"

                // dependencies
                export("com.russhwolf:multiplatform-settings:$multiplatformSettingsVersion")
                if (isDevice) {
                    export("com.russhwolf:multiplatform-settings-ios:$multiplatformSettingsVersion")
                } else {
                    export("com.russhwolf:multiplatform-settings-iossim:$multiplatformSettingsVersion")
                }

                // adds type information for generic parameters to Kotlin/Native
                // added in 1.3.40, currently experimental
//                freeCompilerArgs.add("-Xobjc-generics")
//                debuggable = true
//                freeCompilerArgs.add("-Xembed-bitcode-marker")
//                freeCompilerArgs.add("-g")
            }
        }
    }

    android("android")

    // source set configurations
    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        // multiplatform-settings
        api("com.russhwolf:multiplatform-settings:$multiplatformSettingsVersion")
        // ktor
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-json:$ktorJsonVersion")
        implementation("io.ktor:ktor-client-serialization:$ktorSerializationVersion")
        // coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")
    }
    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        // ktor
        implementation("io.ktor:ktor-client-android:$ktorVersion")
        implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
        implementation("io.ktor:ktor-client-json-jvm:$ktorJsonVersion")
        implementation("io.ktor:ktor-client-serialization-jvm:$ktorSerializationVersion")
        // sqldelight
        implementation("com.squareup.sqldelight:android-driver:$sqldelightVersion")
        // coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    }
    sourceSets["iosMain"].dependencies {
        // ktor
        implementation("io.ktor:ktor-client-ios:$ktorVersion")
        implementation("io.ktor:ktor-client-core-native:$ktorVersion")
        implementation("io.ktor:ktor-client-json-native:$ktorJsonVersion")
        implementation("io.ktor:ktor-client-serialization-native:$ktorVersion") //?
        if (isDevice) {
            implementation("io.ktor:ktor-client-serialization-iosarm64:$ktorSerializationVersion")
        } else {
            implementation("io.ktor:ktor-client-serialization-iosx64:$ktorSerializationVersion")
        }
        // sqldelight
        implementation("com.squareup.sqldelight:ios-driver:$sqldelightVersion")
        // coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutinesVersion")
    }
    sourceSets["commonTest"].dependencies{
        // kotlin.test
        // placeholder for test frameworks
        implementation("org.jetbrains.kotlin:kotlin-test-common:$kotlin_version")
        implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlin_version")
    }
    sourceSets["androidTest"].dependencies{
        // specific implementation of test framework
        implementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
        implementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    }
    sourceSets["iosTest"].dependencies{
        // no dependencies required. kotlin.test implementation is build in kotlin/native
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
        val androidCompileSdkVersion: Int by rootProject.extra // rootProject.extra.properties["androidCompileSdkVersion"] as Int
        compileSdkVersion(androidCompileSdkVersion)
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


// https://github.com/Kotlin/mpp-example/blob/master/greeting/build.gradle#L80
val iosTest by tasks.registering {
    // other tasks that need to be executed before this one
    dependsOn("linkDebugTestIos")

    // general information about this task
    group = JavaBasePlugin.VERIFICATION_GROUP // folder/organize gradle tasks
    description = "Runs tests for target 'ios' on an iOS simulator" // description

    // it is possible to pass a specific device as property, when calling this task, default is iPhone 11
    val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 11"

    doLast {
        // get location where linkDebugTestIos put binaries
        val binary = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getTest("DEBUG").outputFile
        exec {
            // xcrun xCode commandLine tools
            // simctl is used to interact with iOS simulator from commandLine, similar to adb in android
            // xcrun simctl list    show all available simulators
            // xcrun simctl spawn   spawn a process by executing a given executable on a device
            // xcrun simctl help    show all possible sub-commands
            // For sub-commands that require a <device> argument, you may specify a device UDID
            // or the special "booted" string which will cause simctl to pick a booted device.
            commandLine("xcrun", "simctl", "spawn", "--standalone", device, binary.absolutePath)
        }
    }
}
