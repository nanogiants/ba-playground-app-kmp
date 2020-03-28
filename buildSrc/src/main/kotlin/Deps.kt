object Versions {
  const val constraintLayout = "1.1.3"
  const val coroutines = "1.3.2"
  const val coreKtx = "1.2.0"
  const val dagger = "2.26"
  const val ktor = "1.3.1"
  const val material = "1.2.0-alpha05"
  const val navigation = "2.2.1"
  const val timber = "4.7.1"
  const val appcompat = "1.1.0"
  const val glide = "4.11.0"
  const val lifecycle = "2.2.0"
  const val multiplatformSettings = "0.5"
  const val sqldelight = "1.2.1"
}

object Deps {

  const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
  const val material = "com.google.android.material:material:${Versions.material}"

  object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val androidProcessor =
      "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
  }

  object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
  }

  object Coroutines {
    const val android =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val core =
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coreCommon =
      "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
    const val coreNative =
      "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
  }

  object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout =
      "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx =
      "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val navigationFragment_ktx =
      "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
  }

  object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
    const val stdlibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common"
    const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7"
  }

  object MultiplatformSettings {
    const val multiplatformSettings =
      "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
    const val iosarm64 =
      "com.russhwolf:multiplatform-settings-iosarm64:${Versions.multiplatformSettings}"
    const val iosx64 =
      "com.russhwolf:multiplatform-settings-iosx64:${Versions.multiplatformSettings}"
  }

  object Ktor {
    const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val coreJvm = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
    const val coreNative = "io.ktor:ktor-client-core-native:${Versions.ktor}"
    const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val json = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val jsonJvm = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
    const val jsonNative = "io.ktor:ktor-client-json-native:${Versions.ktor}"
    const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val serializationNative = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
    const val serializationIosarm64 =
      "io.ktor:ktor-client-serialization-iosarm64:${Versions.ktor}"
    const val serializationIosx64 = "io.ktor:ktor-client-serialization-iosx64:${Versions.ktor}"
  }

  object SqlDelight {
    const val androidDriver =
      "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
    const val iosDriver = "com.squareup.sqldelight:ios-driver:${Versions.sqldelight}"
  }
}