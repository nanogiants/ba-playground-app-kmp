
# BA Playground KMP
TODO: description

## Usage
TODO: android app
TODO: ios app
TODO: task to generate strings


## Usage from iOS


## Usage from Android


## Project structure/architecture


## 

## Changelog

In diesem Bereich werden wichtige Veränderungen am Projekt dokumentiert.


### Sa, 09.11.2019

Problem: in androidMain besthet kein Zugriff auf android spezifische Klassen wie z.B. Context.
Lösung: Es darf nicht als target jvm verwendet werden sondern auch android als preset. Auch wenn dies im offiziellen JetBrains Tutorial nicht gemacht wird.
Änderungen:

```
// build.gradle.kts

id("com.android.library") 
id("kotlin-android-extensions")

...

//  jvm("android")
    android("android")

...
android {
    defaultConfig {
        compileSdkVersion(28)
    }
}

```
Es wurde ein Ordner "main" erstellt, dieser enthält ein leeres AndroidManifest. Diese Datei wird anscheined benötigt, da aus ihr das package ausgelesen wird.
