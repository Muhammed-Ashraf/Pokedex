plugins {
    `kotlin-dsl`          // Supports Kotlin DSL for Gradle scripts
}

group = "com.example.pokedex.compose.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "example.pokedex.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "example.pokedex.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "example.pokedex.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "example.pokedex.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "example.pokedex.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "example.pokedex.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("spotless") {
            id = "example.pokedex.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}