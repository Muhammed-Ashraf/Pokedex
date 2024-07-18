//Enable a Gradle feature preview for type-safe project accessors.
// This feature allows you to reference projects in a type-safe manner in the build scripts.
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Configure the dependency resolution management settings.
dependencyResolutionManagement {
    // Specify the repositories to use for resolving dependencies.
    repositories {
        google()       // Add Google's Maven repository.
        mavenCentral() // Add Maven Central repository.
    }
    // Define version catalogs to centralize dependency versions.
    versionCatalogs {
        // Create a version catalog named "libs".
        create("libs") {
            // Load the versions and dependencies from the specified TOML file.
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

// Include the "convention" module in the build.
// This allows Gradle to recognize and build the "convention" module as part of the project.
include(":convention")

