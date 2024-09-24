// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Android Application plugin for building Android applications
    alias(libs.plugins.android.application) apply false

    // Android Library plugin for building Android library modules
    alias(libs.plugins.android.library) apply false

    // Android Testing plugin for writing and running tests in Android projects
    alias(libs.plugins.android.test) apply false

    // Kotlin plugin for enabling Kotlin support in Android applications and libraries
    alias(libs.plugins.kotlin.android) apply false

    // Parcelize plugin for simplifying the process of making classes parcelable
    alias(libs.plugins.kotlin.parcelize) apply false

    // Kotlinx Serialization plugin for serializing and deserializing Kotlin objects
    alias(libs.plugins.kotlinx.serialization) apply false

    // Kotlin Symbol Processing (KSP) plugin for annotation processing in Kotlin
    alias(libs.plugins.ksp) apply false

    // Compose Compiler plugin for Jetpack Compose UI toolkit
    alias(libs.plugins.compose.compiler) apply false

    // Hilt plugin for dependency injection in Android using Dagger
    alias(libs.plugins.hilt.plugin) apply false

    // Spotless plugin for maintaining consistent code style in the project
    alias(libs.plugins.spotless)

    // Baseline Profile plugin for optimizing app performance by precompiling code
    alias(libs.plugins.baselineprofile) apply false
}