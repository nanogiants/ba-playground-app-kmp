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
  val ktor_version: String by rootProject.extra
  val sqldelight_version: String by rootProject.extra
  val multiplatformSettings_version: String by rootProject.extra
  val coroutines_version: String by rootProject.extra

  // target configurations
  // select iOS target platform depending on the Xcode environment variables
  val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true

  val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
    if (isDevice)
      ::iosArm64
    else
      ::iosX64
  // configure target ios
  iOSTarget("ios") {
    binaries {
      framework {
        // name, is used in swift to import the framework
        baseName = "SharedPlayground"

        // dependencies
        export("com.russhwolf:multiplatform-settings:$multiplatformSettings_version")
        if (isDevice) {
          export("com.russhwolf:multiplatform-settings-iosarm64:$multiplatformSettings_version")
        } else {
          export("com.russhwolf:multiplatform-settings-iosx64:$multiplatformSettings_version")
        }
      }
    }
  }
  // configure target android
  android("android")

  // source set configurations
  sourceSets["commonMain"].dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
    api("com.russhwolf:multiplatform-settings:$multiplatformSettings_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-json:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutines_version")
  }
  sourceSets["androidMain"].dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-serialization-jvm:$ktor_version")
    implementation("com.squareup.sqldelight:android-driver:$sqldelight_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
  }
  sourceSets["iosMain"].dependencies {
    implementation("io.ktor:ktor-client-ios:$ktor_version")
    implementation("io.ktor:ktor-client-core-native:$ktor_version")
    implementation("io.ktor:ktor-client-json-native:$ktor_version")
    implementation("io.ktor:ktor-client-serialization-native:$ktor_version")
    if (isDevice) {
      implementation("io.ktor:ktor-client-serialization-iosarm64:$ktor_version")
    } else {
      implementation("io.ktor:ktor-client-serialization-iosx64:$ktor_version")
    }
    implementation("com.squareup.sqldelight:ios-driver:$sqldelight_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$coroutines_version")
  }
  sourceSets["commonTest"].dependencies {
    // kotlin.test
    // placeholder for test frameworks
    implementation("org.jetbrains.kotlin:kotlin-test-common:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlin_version")
  }
  sourceSets["androidTest"].dependencies {
    // specific implementation of test framework
    implementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
  }
  sourceSets["iosTest"].dependencies {
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

// Inspired by official Kotlin Multiplatform tutorial
// https://play.kotlinlang.org/hands-on/Targeting%20iOS%20and%20Android%20with%20Kotlin%20Multiplatform/03_CreatingSharedCode
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
