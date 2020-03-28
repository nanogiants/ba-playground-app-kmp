
plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
}

android {
  //
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(Deps.Kotlin.stdlibJdk7)

  // ui and layout
  implementation(Deps.AndroidX.appcompat)
  implementation(Deps.AndroidX.coreKtx)
  implementation(Deps.AndroidX.constraintLayout)
  implementation(Deps.material)

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

  implementation(project(":shared"))
}