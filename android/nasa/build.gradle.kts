
plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
}

android {
  // fix ktor prevents the build
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

  // test
  testImplementation(DepsTest.junit)
  testImplementation(DepsTest.runner)
  testImplementation(DepsTest.espressoCore)

  // coroutines
  implementation(Deps.AndroidX.lifecycleRuntimeKtx)

  // dagger
  implementation(Deps.Dagger.dagger)
  kapt(Deps.Dagger.compiler)
  implementation(Deps.Dagger.android)
  kapt(Deps.Dagger.androidProcessor)
  implementation(Deps.Dagger.androidSupport)

  // glide
  implementation(Deps.Glide.glide)
  kapt(Deps.Glide.compiler)

  // modules
  implementation(project(":shared"))
}
