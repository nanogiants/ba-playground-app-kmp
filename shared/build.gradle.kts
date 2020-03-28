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
        export(Deps.MultiplatformSettings.multiplatformSettings)
        if (isDevice) {
          export(Deps.MultiplatformSettings.iosarm64)
        } else {
          export(Deps.MultiplatformSettings.iosx64)
        }
      }
    }
  }
  // configure target android
  android("android")

  // source set configurations
  sourceSets["commonMain"].dependencies {
    implementation(Deps.Kotlin.stdlibCommon)
    api(Deps.MultiplatformSettings.multiplatformSettings)
    implementation(Deps.Ktor.core)
    implementation(Deps.Ktor.json)
    implementation(Deps.Ktor.serialization)

    implementation(Deps.Coroutines.coreCommon)
  }
  sourceSets["androidMain"].dependencies {
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.Ktor.android)
    implementation(Deps.Ktor.coreJvm)
    implementation(Deps.Ktor.jsonJvm)
    implementation(Deps.Ktor.serializationJvm)
    implementation(Deps.SqlDelight.androidDriver)
    implementation(Deps.Coroutines.android)
  }
  sourceSets["iosMain"].dependencies {
    implementation(Deps.Ktor.ios)
    implementation(Deps.Ktor.coreNative)
    implementation(Deps.Ktor.jsonNative)
    implementation(Deps.Ktor.serializationNative)
    if (isDevice) {
      implementation(Deps.Ktor.serializationIosarm64)
    } else {
      implementation(Deps.Ktor.serializationIosx64)
    }
    implementation(Deps.SqlDelight.iosDriver)
    implementation(Deps.Coroutines.coreNative)
  }
  sourceSets["commonTest"].dependencies {
    implementation(DepsTest.KotlinTest.common)
    implementation(DepsTest.KotlinTest.annotationsCommon)
  }
  sourceSets["androidTest"].dependencies {
    implementation(DepsTest.KotlinTest.test)
    implementation(DepsTest.KotlinTest.junit)
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
    compileSdkVersion(AndroidConstants.androidCompileSdkVersion)
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
