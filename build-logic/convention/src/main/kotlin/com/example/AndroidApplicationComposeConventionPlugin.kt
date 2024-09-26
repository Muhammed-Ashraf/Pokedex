package com.example

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.example.pokedex.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

// This class defines a custom Gradle plugin for configuring Android applications with Jetpack Compose
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    // The apply function is called when this plugin is applied to a Gradle project
    override fun apply(target: Project) {
        // 'with(target)' provides access to the project object for easy configuration
        with(target) {
            // Applies the Android Application plugin, necessary for Android app development
            pluginManager.apply("com.android.application")

            // Retrieves the Android-specific project extension (BaseAppModuleExtension)
            // which contains settings like compileSdkVersion, defaultConfig, etc.
            val extension = extensions.getByType<BaseAppModuleExtension>()

            // Calls a custom method to configure Jetpack Compose settings for the Android project
            configureAndroidCompose(extension)
        }
    }
}