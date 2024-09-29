

// BaseAppModuleExtension is part of the Android Gradle Plugin, 
// allowing access to Android-specific configurations like compileSdk, defaultConfig, etc.

// Gradle's Plugin and Project classes are used to define and apply custom plugins for a project.

// Gradle Kotlin DSL functions, 'configure' simplifies extension configuration, 
// and 'getByType' retrieves a specific type of extension.

// Kotlin's JvmTarget is used to specify the target JVM bytecode version.

// KotlinAndroidProjectExtension is used to configure Kotlin-specific settings in Android projects.
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.example.pokedex.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

// A custom Gradle plugin class that configures Android projects with standardized settings for Android and Kotlin.
class AndroidApplicationConventionPlugin : Plugin<Project> {

    // The apply function is called when the plugin is applied to a project.
    // It configures the necessary plugins and settings for the Android application.
    override fun apply(target: Project) {

        // 'with(target)' simplifies access to the project object, allowing multiple configurations within this block.
        with(target) {

            // Configure the project's plugin manager to apply the necessary plugins
            with(pluginManager) {
                // Applies the Android Application plugin. This is necessary for any project
                // that builds an Android app, setting up Android-specific tasks and configurations.
                apply("com.android.application")

                // Applies the Kotlin Android plugin, which enables Kotlin support for Android development,
                // allowing the project to compile Kotlin code in an Android environment.
                apply("org.jetbrains.kotlin.android")
            }

            // Configure the Android-specific settings for the project using BaseAppModuleExtension.
            // BaseAppModuleExtension allows access to core Android build configurations such as SDK versions, flavors, etc.
            extensions.configure<BaseAppModuleExtension> {

                // Calls a custom function (imported from Skydoves' Pokedex project) to configure Kotlin Android settings.
                // This function likely sets important Kotlin options, such as the Kotlin compiler settings.
                configureKotlinAndroid(this)

                // Set the target SDK version of the Android app to 35.
                // The target SDK defines the Android API level that the app is designed to run on.
                defaultConfig.targetSdk = 35
            }

            // Retrieve the KotlinAndroidProjectExtension, which contains Kotlin-specific settings for Android projects.
            // This extension allows you to configure Kotlin compilation options like JVM target and more.
            extensions.getByType<KotlinAndroidProjectExtension>().apply {

                // Calls the same configureKotlinAndroid function to apply custom Kotlin settings to the project.
                // This function is applied here to ensure that Kotlin settings are consistently applied across the project.
                configureKotlinAndroid(this)
            }
        }
    }
}
