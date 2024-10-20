plugins {
    // Applies a custom plugin for the Android library module.
    // This is likely specific to the Pokedex project and configures the module
    // to behave as a library (producing an .aar instead of an .apk).
    id("example.pokedex.android.library")

    // Applies Kotlin Serialization plugin, which provides functionality
    // to serialize/deserialize Kotlin data classes to/from formats like JSON.
    alias(libs.plugins.kotlinx.serialization)

    // Applies Kotlin Parcelize plugin, which simplifies the implementation of
    // Parcelable interface. Used in Android to pass objects between components
    // (e.g., activities, fragments).
    alias(libs.plugins.kotlin.parcelize)

    // Applies the Kotlin Symbol Processing (KSP) plugin. KSP is used for
    // annotation processing and generates additional source code at compile time.
    // It's a faster, more efficient alternative to `kapt`.
    alias(libs.plugins.ksp)

    // Applies the Spotless plugin, which is a code formatting tool. It helps ensure
    // that the code adheres to a consistent style throughout the project.
    id("example.pokedex.spotless")
}

android {
    // Specifies the namespace for this library module. It defines the base package
    // name used for this module and organizes its classes. This is also used in the
    // AndroidManifest and affects the R class (resources) generation.
    namespace = "com.example.pokedex.compose.core.model"
}

dependencies {
    // Adds a compile-only dependency for Jetpack Compose's stable marker.
    // Compile-only dependencies are required only at compile time and will
    // not be included in the resulting build (i.e., they won't be packaged with the .aar).
    // This particular library likely marks stable Compose APIs.
    compileOnly(libs.compose.stable.marker)

    // Adds Kotlin Serialization library specifically for handling JSON serialization.
    // This is a runtime dependency, meaning it will be included in the final build.
    // It allows you to easily convert Kotlin objects to JSON and vice versa.
    implementation(libs.kotlinx.serialization.json)

    // Adds a dependency for Kotlin's immutable collections library.
    // The `api` configuration means that other modules that depend on this
    // library will also have access to this dependency (i.e., it's exposed transitively).
    api(libs.kotlinx.immutable.collection)
}
