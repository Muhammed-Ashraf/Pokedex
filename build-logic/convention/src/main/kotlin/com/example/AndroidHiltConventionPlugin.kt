package com.example// Import necessary classes for defining a custom Gradle plugin and managing dependencies

// Plugin and Project classes from the Gradle API, used to define and apply custom Gradle plugins to projects
import org.gradle.api.Plugin
import org.gradle.api.Project

// VersionCatalogsExtension is used to access the version catalog, which centralizes dependency version management
import org.gradle.api.artifacts.VersionCatalogsExtension

// Gradle Kotlin DSL functions to simplify configuration of dependencies and project extensions
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

// This class defines a custom Gradle plugin called 'AndroidHiltConventionPlugin'.
// The plugin is responsible for applying the necessary plugins (Hilt and KSP) and adding Hilt-related dependencies
// from a shared version catalog to the Android project. It standardizes the setup for Hilt in Android modules.
class AndroidHiltConventionPlugin : Plugin<Project> {

  // The 'apply' function is executed when this plugin is applied to a project.
  // It configures the project by applying required plugins and adding Hilt dependencies.
  override fun apply(target: Project) {
    
    // 'with(target)' is a Kotlin scope function that allows us to configure the target project
    with(target) {
      
      // Use the plugin manager to apply the necessary Gradle plugins for Hilt and Kotlin Symbol Processing (KSP)
      with(pluginManager) {
        
        // Apply the Hilt Android plugin, which sets up Hilt for dependency injection in the Android project
        apply("dagger.hilt.android.plugin")
        
        // Apply the Kotlin Symbol Processing (KSP) plugin, which is required for Hilt's code generation.
        // KSP is a tool that processes annotations in Kotlin and generates code during the build process.
        apply("com.google.devtools.ksp")
      }

      // Retrieve the version catalog extension from the project.
      // The version catalog ('libs') centralizes the versions and coordinates of dependencies used in the project.
      // This allows for consistent version management across different modules.
      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

      // Configure the project's dependencies block to include the required Hilt libraries and the Hilt compiler.
      dependencies {
        
        // Add the Hilt Android library ('hilt.android') to the project as an implementation dependency.
        // This library provides the core functionality for Dagger Hilt to perform dependency injection in Android.
        add("implementation", libs.findLibrary("hilt.android").get())
        
        // Add the AndroidX Hilt Navigation Compose library ('androidx.hilt.navigation.compose') to the project.
        // This library integrates Hilt with Jetpack Compose's navigation system, allowing for dependency injection
        // within Compose navigation components.
        add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        
        // Add the Hilt compiler ('hilt.compiler') as a KSP dependency.
        // The compiler processes the Hilt annotations at compile time to generate the necessary code for dependency injection.
        add("ksp", libs.findLibrary("hilt.compiler").get())
      }
    }
  }
}
