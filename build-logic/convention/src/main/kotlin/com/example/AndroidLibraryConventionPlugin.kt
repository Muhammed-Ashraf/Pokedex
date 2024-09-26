package com.example// Import necessary classes for defining a custom Gradle plugin and managing Android library configurations
import com.android.build.gradle.LibraryExtension // Import for Android library configuration
import com.example.pokedex.configureKotlinAndroid
import org.gradle.api.Plugin // Import for defining a Gradle plugin
import org.gradle.api.Project // Import for interacting with the Gradle project
import org.gradle.kotlin.dsl.configure // Import for configuring DSL extensions
import org.gradle.kotlin.dsl.getByType // Import for retrieving project extensions by type
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

// This class defines a custom Gradle plugin called 'AndroidLibraryConventionPlugin'.
// The purpose of this plugin is to configure an Android library module with standard settings
// that facilitate the development of Android libraries, particularly with Kotlin support.
class AndroidLibraryConventionPlugin : Plugin<Project> {

  // The 'apply' function is executed when this plugin is applied to a project.
  // It contains the logic for configuring the target project as an Android library.
  override fun apply(target: Project) {
    
    // 'with(target)' is a Kotlin scope function that allows for configuring the target project.
    // It simplifies access to the project's properties and methods, reducing boilerplate code.
    with(target) {
      
      // 'with(pluginManager)' allows applying plugins concisely within this scope.
      with(pluginManager) {
        
        // Apply the Android Library plugin to the project.
        // This plugin is required for Gradle to treat the module as an Android library,
        // allowing it to define reusable components, such as UI elements and business logic.
        apply("com.android.library")
        
        // Apply the Kotlin Android plugin to enable Kotlin support in the Android library.
        // This allows the use of Kotlin features and extensions in the Android environment.
        apply("org.jetbrains.kotlin.android")
      }

      // Retrieve the LibraryExtension from the project.
      // The LibraryExtension provides configurations and settings specific to Android library modules,
      // such as default configurations, build types, and product flavors.
      extensions.configure<LibraryExtension> {
        
        // Call the custom function 'configureKotlinAndroid' to set up Kotlin-related settings.
        // This function might configure the Kotlin version, enable Kotlin features, or set compiler options.
        configureKotlinAndroid(this)
        
        // Set the default target SDK version for the library module to 35.
        // The target SDK specifies the highest Android API level that the library is tested against,
        // ensuring compatibility with newer Android versions.
        defaultConfig.targetSdk = 35
      }

      // Retrieve the KotlinAndroidProjectExtension to apply additional Kotlin settings.
      // This extension provides options specific to Kotlin projects within the Android context.
      extensions.getByType<KotlinAndroidProjectExtension>().apply {
        
        // Call the custom function 'configureKotlinAndroid' again to ensure consistent Kotlin settings.
        // This is useful for standardizing the configuration across the project.
        configureKotlinAndroid(this)
      }
    }
  }
}
