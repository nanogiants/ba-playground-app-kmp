include(":app", ":settings", ":nasa", ":pixelsort", ":shared", ":notes", ":fibonacci", ":game")
rootProject.name = "KmpPlayground"

project(":app").projectDir = File("android/app")
project(":settings").projectDir = File("android/settings")
project(":nasa").projectDir = File("android/nasa")
project(":pixelsort").projectDir = File("android/pixelsort")
project(":notes").projectDir = File("android/notes")
project(":fibonacci").projectDir = File("android/fibonacci")
project(":game").projectDir = File("android/game")

// To use SQLDelight in kotlin multiplatform the gradle metadata feature is required
enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.namespace == "com.android") {
        useModule("com.android.tools.build:gradle:${requested.version}")
      }
      if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
        useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
      }
    }
  }
}