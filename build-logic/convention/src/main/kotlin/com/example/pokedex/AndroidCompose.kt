package com.example.pokedex

// These imports bring in the necessary classes and functions from Gradle, Kotlin DSL,
// and Android build API to configure the Android project and Jetpack Compose support.
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

// This function configures Compose-specific options for the Android project.
// It takes two parameters:
// 1. 'Project' (implicit receiver): The Gradle project in which this function is applied.
// 2. 'commonExtension': A CommonExtension type, used to configure Android-specific build features like Compose.

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    // Applies the Kotlin Compose plugin ("org.jetbrains.kotlin.plugin.compose") to the project.
    // This plugin is necessary for building Jetpack Compose UI components in the Android project.
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

// Enables Jetpack Compose in the project by setting 'compose = true'.
    // This is required for the project to use Compose UI components.
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
    // Enables the "strong skipping mode" in the Compose compiler.
    // This is an optimization that helps speed up build times by skipping unnecessary
    // parts of the Compose compilation process when they aren't needed.
    extensions.configure<ComposeCompilerGradlePluginExtension> {
        enableStrongSkippingMode = true
// Specifies where the Compose compiler reports should be saved.
        // The reports will be stored in the 'build/compose_compiler' directory.
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }
}