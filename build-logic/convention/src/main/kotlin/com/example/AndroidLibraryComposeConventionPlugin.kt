

// LibraryExtension is part of the Android Gradle Plugin and provides configurations specific to Android library modules
import com.android.build.gradle.LibraryExtension
import com.example.pokedex.configureAndroidCompose

// Plugin and Project classes from the Gradle API, used to define and apply custom Gradle plugins to projects
import org.gradle.api.Plugin
import org.gradle.api.Project

// Gradle Kotlin DSL function to simplify the retrieval of project extensions by type
import org.gradle.kotlin.dsl.getByType

// This class defines a custom Gradle plugin called 'AndroidLibraryComposeConventionPlugin'.
// The purpose of this plugin is to configure an Android library module to support Jetpack Compose.
// It simplifies the setup process by applying necessary configurations automatically.
class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    // The 'apply' function is executed when this plugin is applied to a project.
    // It configures the target project to enable Jetpack Compose support in an Android library module.
    override fun apply(target: Project) {

        // 'with(target)' is a Kotlin scope function that allows for configuring the target project.
        // It simplifies access to the project's properties and methods.
        with(target) {

            // Apply the Android Library plugin to the project.
            // This plugin is required for Gradle to treat the module as an Android library,
            // allowing it to define reusable components, such as UI elements and business logic.
            pluginManager.apply("com.android.library")

            // Retrieve the LibraryExtension from the project.
            // The LibraryExtension provides configurations and settings specific to Android library modules,
            // such as default configurations, build types, and product flavors.
            val extension = extensions.getByType<LibraryExtension>()

            // Call the custom function 'configureAndroidCompose' to set up Jetpack Compose for the library module.
            // This function likely configures necessary options for using Jetpack Compose,
            // such as enabling Compose support, setting Compose compiler options, etc.
            configureAndroidCompose(extension)
        }
    }
}
