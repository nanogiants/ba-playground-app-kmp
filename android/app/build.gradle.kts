
plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
}

android {
  // fix ktor prevents the build, nasa module
  packagingOptions {
    exclude("META-INF/*.kotlin_module")
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(Deps.Kotlin.stdlibJdk7)

  // ui and layout
  implementation(Deps.AndroidX.appcompat)
  implementation(Deps.AndroidX.coreKtx)
  implementation(Deps.AndroidX.constraintLayout)
  implementation(Deps.material)

  // logging
  implementation(Deps.timber)

  // navigation
  implementation(Deps.AndroidX.navigationFragment_ktx)
  implementation(Deps.AndroidX.navigationUiKtx)

  // dagger
  implementation(Deps.Dagger.dagger)
  kapt(Deps.Dagger.compiler)
  implementation(Deps.Dagger.android)
  kapt(Deps.Dagger.androidProcessor)
  implementation(Deps.Dagger.androidSupport)

  // test
  testImplementation(DepsTest.junit)
  testImplementation(DepsTest.runner)
  testImplementation(DepsTest.espressoCore)

  // modules
  implementation(project(":settings"))
  implementation(project(":nasa"))
  implementation(project(":notes"))
  implementation(project(":fibonacci"))
  implementation(project(":pixelsort"))
  implementation(project(":game"))
  implementation(project(":shared"))
}
