package com.example// Import necessary classes for configuring the Spotless plugin in a Gradle project.
import com.diffplug.gradle.spotless.SpotlessExtension // Import for Spotless configuration extension
import org.gradle.api.Plugin // Import for defining a Gradle plugin
import org.gradle.api.Project // Import for interacting with the Gradle project
import org.gradle.kotlin.dsl.configure // Import for configuring DSL extensions

// This class defines a custom Gradle plugin called 'SpotlessConventionPlugin'.
// The purpose of this plugin is to configure Spotless for code formatting and linting
// in a Gradle project, ensuring consistent coding styles across various file types.
class SpotlessConventionPlugin : Plugin<Project> {

  // The 'apply' function is executed when this plugin is applied to a project.
  // It contains the logic for configuring the Spotless plugin.
  override fun apply(target: Project) {
    
    // 'with(target)' is a Kotlin scope function that allows for configuring the target project.
    // It simplifies access to the project's properties and methods, reducing boilerplate code.
    with(target) {
      
      // Apply the Spotless plugin to the project using the plugin manager.
      // This is necessary to enable the Spotless functionality for code formatting.
      pluginManager.apply("com.diffplug.spotless")

      // Retrieve the SpotlessExtension from the project to configure code formatting rules.
      // This extension allows you to set up different formatting options for various file types.
      extensions.configure<SpotlessExtension> {
        
        // Define a variable to hold the build directory as a file tree.
        // This helps exclude build-related files from formatting operations.
        val buildDirectory = layout.buildDirectory.asFileTree
        
        // Configuration block for formatting Kotlin files.
        kotlin {
          // Specify that all .kt files in the project should be targeted for formatting.
          target("**/*.kt")
          
          // Exclude files in the build directory from formatting to avoid unnecessary changes.
          targetExclude(buildDirectory)

          // Configure ktlint (Kotlin linter) settings with specific options.
          ktlint().editorConfigOverride(
            mapOf(
              // Set the standard indent size to 2 spaces.
              "indent_size" to "2",
              // Set the continuation indent size to 2 spaces as well.
              "continuation_indent_size" to "2",
              // Ignore function naming conventions for functions annotated with @Composable.
              "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
            )
          )
          
          // Specify the license header file to be added to each Kotlin file.
          // The header is located in the 'spotless' directory of the root project.
          licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"))
          
          // Remove any trailing whitespace from the end of lines in formatted files.
          trimTrailingWhitespace()
          
          // Ensure that each file ends with a newline character for proper formatting.
          endWithNewline()
        }
        
        // Configuration block for formatting Kotlin script files (.kts).
        format("kts") {
          // Specify that all .kts files should be targeted for formatting.
          target("**/*.kts")
          
          // Exclude files in the build directory from formatting.
          targetExclude(buildDirectory)

          // Specify the license header file for Kotlin script files.
          // Use a regex to avoid applying the header to certain lines.
          licenseHeaderFile(rootProject.file("spotless/spotless.license.kt"), "(^(?![\\/ ]\\*).*$)")
        }
        
        // Configuration block for formatting XML files.
        format("xml") {
          // Specify that all .xml files should be targeted for formatting.
          target("**/*.xml")
          
          // Exclude files in the build directory from formatting.
          targetExclude(buildDirectory)

          // Specify the license header file for XML files.
          // Use a regex to avoid applying the header to certain lines.
          licenseHeaderFile(rootProject.file("spotless/spotless.license.xml"), "(<[^!?])")
        }
      }
    }
  }
}
