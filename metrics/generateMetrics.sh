# create folder for metrics for initial use of this script
mkdir -p ./build/

# create folder for tokei
mkdir -p ./build/tokei

# android metrics with tokei
tokei ../android/nasa/src/main/kotlin --output json > ./build/tokei/android_nasa.json
tokei ../android/pixelsort/src/main/kotlin --output json > ./build/tokei/android_pixelsort.json
tokei ../android/settings/src/main/kotlin --output json > ./build/tokei/android_settings.json

# ios metrics with tokei
tokei ../ios/Playground/UseCases/Pixelsort --output json > ./build/tokei/ios_pixelsort.json
tokei ../ios/Playground/UseCases/Settings --output json > ./build/tokei/ios_settings.json

# shared
tokei ../shared/src/androidMain/kotlin/nasa ../shared/src/commonMain/kotlin/nasa ../shared/src/iosMain/kotlin/nasa --output json > ./build/tokei/shared_nasa.json
tokei ../shared/src/androidMain/kotlin/pixelsort ../shared/src/commonMain/kotlin/pixelsort ../shared/src/iosMain/kotlin/pixelsort --output json > ./build/tokei/shared_nasa.json
tokei ../shared/src/androidMain/kotlin/settings ../shared/src/commonMain/kotlin/settings ../shared/src/iosMain/kotlin/settings --output json > ./build/tokei/shared_nasa.json

