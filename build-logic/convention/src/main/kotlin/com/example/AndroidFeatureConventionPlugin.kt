

// LibraryExtension is part of the Android Gradle Plugin, used for configuring Android library modules
import com.android.build.gradle.LibraryExtension
import com.example.pokedex.configureAndroidCompose
import com.example.pokedex.configureKotlinAndroid


// Gradle Plugin and Project classes, used to define and apply custom Gradle plugins to projects
import org.gradle.api.Plugin
import org.gradle.api.Project

// Gradle Kotlin DSL functions to simplify configuration of extensions and dependencies
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

// KotlinAndroidProjectExtension represents the Kotlin-specific configuration for Android projects
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

// This class defines a custom Gradle plugin for configuring Android feature modules in a modularized project.
// It applies plugins, adds dependencies, and configures Kotlin and Android-specific settings in a standardized way.
class AndroidFeatureConventionPlugin : Plugin<Project> {

    // The apply function is called when this plugin is applied to a Gradle project.
    // It contains the logic for setting up the necessary plugins, dependencies, and project settings.
    override fun apply(target: Project) {

        // 'with(target)' is a Kotlin scope function that simplifies the configuration of the target project
        with(target) {

            // Apply the necessary plugins to the project using the plugin manager
            pluginManager.apply {
                // Apply the Android Library plugin, which sets up this project as an Android library module
                apply("com.android.library")

                // Apply the Kotlin Android plugin, enabling Kotlin support in the Android library project
                apply("org.jetbrains.kotlin.android")
            }

            // Configure the project's dependencies block to include shared modules required by the feature module
            dependencies {
                // Add the core design system module as a dependency, which likely contains shared UI components
                //TODO uncomment
                // add("implementation", project(":core:designsystem"))

                // Add the core navigation module, used for navigation logic and routing in the app
                //TODO uncomment
                //  add("implementation", project(":core:navigation"))

                // Add the core ViewModel module, which contains shared ViewModel classes for managing UI-related data
                //   add("implementation", project(":core:viewmodel"))

                // Add the core data module, which manages data access, handling, and persistence
                   add("implementation", project(":core:data"))

                // Add the core preview module as a 'compileOnly' dependency, likely used for UI previews and not required at runtime
                //TODO uncomment
                //  add("compileOnly", project(":core:preview"))
            }

            // Retrieve and configure the LibraryExtension, which contains Android-specific settings for library modules
            extensions.configure<LibraryExtension> {
                // Configure Kotlin Android settings for this library module using the custom 'configureKotlinAndroid' function.
                // This function likely sets up Kotlin compiler options, language version, and other Kotlin-specific settings.
                configureKotlinAndroid(this)

                // Configure Jetpack Compose settings for this Android library using the custom 'configureAndroidCompose' function.
                // This function ensures that the library is set up to support Jetpack Compose for building UIs.
                configureAndroidCompose(this)

                // Set the target SDK version to 35, meaning this library is designed to run on Android API level 35.
                // The target SDK version tells the system which Android API level the library is targeting for runtime.
                defaultConfig.targetSdk = 35
            }

            // Retrieve and configure the KotlinAndroidProjectExtension, which contains Kotlin-specific project settings
            extensions.getByType<KotlinAndroidProjectExtension>().apply {
                // Apply the same custom Kotlin Android configuration to this project
                // This ensures consistent Kotlin settings across the project, such as JVM target and language version.
                configureKotlinAndroid(this)
            }
        }
    }
}
