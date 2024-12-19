package com.example.pokedex
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

// Imports:
// - `CommonExtension`: Used to configure Android project features like `compileSdk` and `minSdk`.
// - `JavaVersion`: Represents the Java version to use for source and target compatibility.
// - `Project`: Refers to the Gradle project being configured.
// - `JvmTarget`: Used to specify the target JVM version for Kotlin code compilation.
// - `KotlinAndroidProjectExtension`: Allows configuring Kotlin-specific options for Android projects,
//   like compiler options and experimental features.

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    // This function configures essential Android project settings related to Kotlin.
    // It modifies the Android build configuration by setting SDK versions, Java compatibility,
    // and linting rules using the provided `commonExtension` object.

    // Sets the Android SDK version used for compiling the project to API level 35.
    commonExtension.apply {
        compileSdk = 35

        // Sets the minimum Android SDK version supported by the app to API level 21 (Android 5.0 Lollipop).
        defaultConfig {
            minSdk = 28
        }

        // Configures the Java source and target compatibility to Java 17.
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        // Disables aborting the build on lint errors, allowing the build to continue even if issues are found.
        lint {
            abortOnError = false
        }
    }
}

internal fun Project.configureKotlinAndroid(
    extension: KotlinAndroidProjectExtension,
) {
    // This function sets up Kotlin-specific compiler options, including enabling experimental features
    // for coroutines and Jetpack Compose, setting the JVM target version, and treating warnings as errors.

    // Configures compiler options for Kotlin.
    extension.apply {
        compilerOptions {

            // Treats all Kotlin warnings as errors, making the build fail if warnings are found.
            // The behavior is controlled by the `warningsAsErrors` property (defaults to `false` if not set).
            allWarningsAsErrors.set(
                properties["warningsAsErrors"] as? Boolean ?: false
            )

            // Adds additional compiler arguments to enable experimental features and advanced language features.
            freeCompilerArgs.set(
                freeCompilerArgs.getOrElse(emptyList()) + listOf(

                    // Enables support for the context receivers feature in Kotlin (experimental).
                    "-Xcontext-receivers",

                    // Allows usage of APIs marked with `@RequiresOptIn`, indicating experimental status.
                    "-Xopt-in=kotlin.RequiresOptIn",

                    // Enables experimental coroutines APIs from kotlinx.coroutines, including `Flow`.
                    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",

                    // Opts into experimental APIs from Jetpack Compose's Material 3 library.
                    "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",

                    // Enables lifecycle-aware experimental APIs for Compose in androidx.lifecycle.
                    "-Xopt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",

                    // Enables experimental shared transition APIs for Compose animations.
                    "-Xopt-in=androidx.compose.animation.ExperimentalSharedTransitionApi",
                )
            )

            // Sets the target JVM version for Kotlin to 17, ensuring Kotlin code compiles to Java 17 bytecode.
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}
