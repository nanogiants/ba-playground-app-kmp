# create folder for metrics for initial use of this script
mkdir -p ./build/

# create folder for tokei
mkdir -p ./build/tokei

# android metrics with tokei
tokei ../android/fibonacci/src/main/kotlin --output json > ./build/tokei/android_fibonacci.json
tokei ../android/nasa/src/main/kotlin --output json > ./build/tokei/android_nasa.json
tokei ../android/notes/src/main/kotlin --output json > ./build/tokei/android_notes.json
tokei ../android/pixelsort/src/main/kotlin --output json > ./build/tokei/android_pixelsort.json
tokei ../android/settings/src/main/kotlin --output json > ./build/tokei/android_settings.json
tokei ../android/game/src/main/kotlin --output json > ./build/tokei/android_game.json

# ios metrics with tokei
tokei ../ios/Playground/UseCases/Fibonacci --output json > ./build/tokei/ios_fibonacci.json
tokei ../ios/Playground/UseCases/Nasa --output json > ./build/tokei/ios_nasa.json
tokei ../ios/Playground/UseCases/Notes --output json > ./build/tokei/ios_notes.json
tokei ../ios/Playground/UseCases/Pixelsort --output json > ./build/tokei/ios_pixelsort.json
tokei ../ios/Playground/UseCases/Settings --output json > ./build/tokei/ios_settings.json
tokei ../ios/Playground/UseCases/Game --output json > ./build/tokei/ios_game.json

# shared
tokei ../shared/src/androidMain/kotlin/fibonacci ../shared/src/commonMain/kotlin/fibonacci ../shared/src/iosMain/kotlin/fibonacci --output json > ./build/tokei/shared_fibonacci.json
tokei ../shared/src/androidMain/kotlin/nasa ../shared/src/commonMain/kotlin/nasa ../shared/src/iosMain/kotlin/nasa --output json > ./build/tokei/shared_nasa.json
tokei ../shared/src/androidMain/kotlin/notes ../shared/src/commonMain/kotlin/notes ../shared/src/iosMain/kotlin/notes --output json > ./build/tokei/shared_notes.json
tokei ../shared/src/androidMain/kotlin/pixelsort ../shared/src/commonMain/kotlin/pixelsort ../shared/src/iosMain/kotlin/pixelsort --output json > ./build/tokei/shared_pixelsort.json
tokei ../shared/src/androidMain/kotlin/settings ../shared/src/commonMain/kotlin/settings ../shared/src/iosMain/kotlin/settings --output json > ./build/tokei/shared_settings.json
tokei ../shared/src/androidMain/kotlin/game ../shared/src/commonMain/kotlin/game ../shared/src/iosMain/kotlin/game --output json > ./build/tokei/shared_game.json

