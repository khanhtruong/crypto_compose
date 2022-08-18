## README

Crypto compose is one of my first jetpack compose application.

## General

- Platform: Android
- APK version supported: 24-33
- Application type: Finance
- Source code: [Github](https://github.com/khanhtruong/crypto_compose)

## Architecture

The combination of `clean` and `MVVM` architecture. The most reason why I chose MVVM instead MVP is that
ViewModel lib of Google supports lifecycle very well, and it's a unidirectional data flow.
A perfect candidate for compose.

## Technologies used

- IDE: Android studio preview Electric Eel
- Languages: Kotlin, Jetpack compose
- Architecture: MVVM
- Dependencies Injection: [Hilt](https://github.com/googlecodelabs/android-hilt)
- Networking: [Retrofit2](https://github.com/square/retrofit) & [OkHttp3](https://github.com/square/okhttp) & [Moshi](https://github.com/square/moshi)
- Database: [Room](https://github.com/androidx-releases/Room)
- Image loader: [Coil](https://github.com/coil-kt/coil)
- Kotlin flow

## How to install this project?

1. Clone this project to your local PC
2. Import project using android studio
