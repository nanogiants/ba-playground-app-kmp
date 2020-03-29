plugins {
  // kotlin multiplatform plugin
  kotlin("multiplatform")

  // plugins for dependencies: serialization, sqldelighter
  id("kotlinx-serialization")
  id("com.squareup.sqldelight")

  // android plugin and android extensions are necessary for the android target
  id("com.android.library")
  id("kotlin-android-extensions")

  id("org.jetbrains.kotlin.native.cocoapods")
}

// CocoaPods requires version
version = "1.0"

kotlin {
  // target configurations
  // select iOS target platform depending on the Xcode environment variables

  val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true
  if (isDevice)
    iosArm64("ios")
  else
    iosX64("ios")

  // Setting the link flag seems not to work
  // As a result it needs to be set manually in the ios project
  // Playground - Linking - Other Linker Flags - "-lsqlite3"
  // https://github.com/cashapp/sqldelight/issues/1442
//  targets.getByName<KotlinNativeTarget>("ios").compilations.forEach {
//    it.kotlinOptions.freeCompilerArgs += listOf("-linker-options", "-lsqlite3")
//  }

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

kotlin {
  cocoapods {
    summary = "Shared code for playground project"
    homepage = "Link to homepage"
  }
}