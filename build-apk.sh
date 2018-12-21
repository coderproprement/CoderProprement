./gradlew clean
./gradlew test
./gradlew assembleDebug
mkdir -p build/apk
rm app/build/outputs/apk/debug/app-debug.apk build/apk/test.apk
