import com.android.build.gradle.BaseExtension

var androidModules =
  listOf("app", "settings", "nasa", "pixelsort", "shared", "notes", "fibonacci", "game")

plugins {
  id("com.android.application") version BuildConstants.buildToolVersion apply false
  kotlin("android") version KotlinConstants.kotlinVersion apply false
  kotlin("kotlin.extensions") version KotlinConstants.kotlinVersion apply false
}

buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${BuildConstants.buildToolVersion}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinConstants.kotlinVersion}")
    classpath("org.jetbrains.kotlin:kotlin-serialization:${KotlinConstants.kotlinVersion}")
    classpath("com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
    }
  }

}

subprojects {
  afterEvaluate {
    val isAndroidModule =
      androidModules.contains(name) && this.hasProperty("android") && this.name != "shared"
    val isAppModule = name == "app"

    if (isAndroidModule) {
      if (isAppModule) {
        configure<BaseExtension> {
          defaultConfig {
            applicationId = AndroidConstants.androidApplicationId
          }
        }
      }

      plugins.withType<BasePlugin> {
        configure<BaseExtension> {
          compileSdkVersion(AndroidConstants.androidCompileSdkVersion)
          buildToolsVersion(AndroidConstants.androidBuildToolsVersion)
          defaultConfig {
            minSdkVersion(AndroidConstants.androidMinSdkVersion)
            targetSdkVersion(AndroidConstants.androidTargetSdkVersion)
            versionCode = AndroidConstants.androidVersionCode
            versionName = AndroidConstants.androidVersionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
          }
          buildTypes {
            getByName("release") {
              isMinifyEnabled = false
              consumerProguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            }
          }
          sourceSets {
            val main by getting
            main.java.srcDirs("src/main/kotlin")
          }
          compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
          }
          viewBinding {
            isEnabled = true
          }
        }
      }
    }
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}

val generateProjectStrings by tasks.registering(tasks.TwineStringGeneratingTask::class) {
  description = "Generates string resources from localization.txt file"
  localizationFilePath = project.rootDir.absolutePath + "/localization/localization.txt"
  androidProjectRoot = project.rootDir.absolutePath + "/android"
  androidSubProjects = androidModules
  iosProjectRoot = project.rootDir.absolutePath + "/ios"
}