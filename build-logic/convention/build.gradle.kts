plugins {
    `kotlin-dsl`          // Supports Kotlin DSL for Gradle scripts
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}