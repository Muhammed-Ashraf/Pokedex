plugins {
    // Applies a custom plugin for the Android library module.
    // This plugin is likely specific to the Pokedex project and configures the module
    // to behave as an Android library (producing an .aar instead of an .apk).
    // This allows it to be consumed by other Android projects.
    id("example.pokedex.android.library")

    // Applies the Kotlin Symbol Processing (KSP) plugin.
    // KSP is used for annotation processing and generates additional code at compile time.
    // It's more efficient than `kapt` and can be used for libraries like Room and Hilt.
    alias(libs.plugins.ksp)

    // Applies the Spotless plugin, which is a code formatting and linting tool.
    // It helps ensure consistent code style and formatting throughout the project.
    // Spotless checks the code style before build, making sure the codebase stays clean.
    id("example.pokedex.spotless")

    // Applies a custom plugin for Hilt, which is a dependency injection library for Android.
    // Hilt helps manage the app's dependencies, making them easier to inject into different
    // parts of the app like activities, fragments, and ViewModels.
    id("example.pokedex.android.hilt")
}

android {
    // Specifies the namespace for this Android library module. The namespace defines
    // the package structure for the module and impacts R class (resources) generation.
    // In this case, the package is set to "com.example.pokedex.compose.core.database".
    namespace = "com.example.pokedex.compose.core.database"

    defaultConfig {
        // The `ksp` block is used to provide arguments to the Kotlin Symbol Processing plugin.
        // Here, the argument `room.schemaLocation` specifies the location where Room will
        // store the database schema files. These schema files are used to track database versions,
        // which allows Room to perform automatic migrations between different database versions.
        // This setup enables Room's auto-migration feature to help manage database updates.
        // Reference: https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    // The `sourceSets` block defines where source files for the test configuration should be located.
    // Here, it configures the test source set to include the schema directory as an asset.
    // This ensures that when tests run, they can access the database schemas stored in the specified directory.
    sourceSets.getByName("test") {
        assets.srcDir(files("$projectDir/schemas"))
    }
}

dependencies {
    // Adds a dependency on the core model module of the project.
    // This means this module can use any code (e.g., data models) from the core model module.
    implementation(projects.core.model)

    // Adds a dependency on the core test module for testing purposes.
    // This allows access to shared test utilities and configurations from the core test module.
    testImplementation(projects.core.test)

    // Kotlin Coroutines library for Android, which helps manage background operations
    // and asynchronous code execution. It allows you to write clean, non-blocking code.
    implementation(libs.kotlinx.coroutines.android)

    // Testing library for coroutines. This is used to test coroutine code in a controlled
    // environment, simulating how coroutines would behave during runtime.
    testImplementation(libs.kotlinx.coroutines.test)

    // Room database dependencies:
    // 1. Room runtime library: This is the main library that provides all database functionality.
    //    It allows you to define database tables as Kotlin objects (entities) and provides DAOs (Data Access Objects)
    //    to perform SQL queries on the database.
    // 2. Room KTX: This is a set of Kotlin extensions for Room that make it easier to work with, providing
    //    Kotlin-friendly APIs (e.g., coroutines support for database operations).
    // 3. Room compiler: This is the KSP processor used to generate the code for Room at compile time. It automatically
    //    generates the necessary code for Room's DAOs and database classes.
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Adds a testing library for Android Architecture components like Room.
    // This library provides tools to help you test Android's architecture components, such as the lifecycle
    // of ViewModels and databases.
    testImplementation(libs.androidx.arch.core.testing)

    // Adds Kotlinx Serialization JSON library.
    // This library is used for serializing and deserializing Kotlin objects to and from JSON.
    // It provides an easy-to-use API for working with JSON data and Kotlin's data classes.
    implementation(libs.kotlinx.serialization.json)

    // Unit testing dependencies:
    // 1. JUnit: The standard unit testing framework for Java and Kotlin. It allows you to write and run
    //    tests for your application's code logic.
    // 2. AndroidX Test Core: Provides core testing utilities for Android, such as running instrumented tests.
    // 3. Robolectric: A testing framework that allows you to run Android unit tests on the JVM (Java Virtual Machine).
    //    This makes tests faster since you don’t need to run them on an actual Android device or emulator.
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.robolectric)
}
