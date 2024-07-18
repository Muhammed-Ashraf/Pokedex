/*

This snippet of code is part of a Gradle build script using Kotlin DSL. It sets up plugin aliases and specifies whether they should be applied immediately. Here's a breakdown of what each line does:

plugins { ... }: This block is used to declare plugins that are applied to the project.

alias(libs.plugins.android.application) apply false: This line creates an alias for the Android application plugin and specifies that it should not be applied immediately. The apply false means that you will apply this plugin later in specific modules where it's needed.

alias(libs.plugins.android.library) apply false: Similar to the previous line, this creates an alias for the Android library plugin without applying it immediately.

alias(libs.plugins.android.test) apply false: Creates an alias for the Android test plugin, also not applied immediately.

alias(libs.plugins.kotlin.android) apply false: Creates an alias for the Kotlin Android plugin, without applying it immediately.

alias(libs.plugins.kotlin.parcelize) apply false: Creates an alias for the Kotlin Parcelize plugin, without applying it immediately.

alias(libs.plugins.kotlinx.serialization) apply false: Creates an alias for the Kotlinx Serialization plugin, without applying it immediately.

alias(libs.plugins.ksp) apply false: Creates an alias for the Kotlin Symbol Processing (KSP) plugin, without applying it immediately.

alias(libs.plugins.compose.compiler) apply false: Creates an alias for the Compose compiler plugin, without applying it immediately.

alias(libs.plugins.hilt.plugin) apply false: Creates an alias for the Hilt plugin, without applying it immediately.

alias(libs.plugins.spotless): Creates an alias for the Spotless plugin and applies it immediately. Spotless is a code formatting plugin.

alias(libs.plugins.baselineprofile) apply false: Creates an alias for the baseline profile plugin, without applying it immediately.

This setup allows you to manage plugin versions and application across different modules of your project in a centralized manner, improving maintainability and reducing redundancy.

 */

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.baselineprofile) apply false
}
