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