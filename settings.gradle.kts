@file:Suppress("UnstableApiUsage")

//TODO uncomment
//include(":baselineprofile")

/*
The enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") line is used in a Gradle build script
 to enable a preview feature that allows for type-safe access to project properties.

Here's what it does:

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS"): This enables the "type-safe project
accessors" feature in Gradle.

Type-Safe Project Accessors: This feature improves the way you access project properties
 and tasks in a multi-module Gradle build. It provides a more type-safe and IDE-friendly
  way to reference project properties and tasks, reducing the chances of errors due to
   typos and making your build scripts easier to maintain.

In practical terms, it means you can access project properties and tasks using a
type-safe syntax, which is generally more robust and less error-prone. For example, instead of using string literals to refer to projects and tasks, you can use a more direct and type-safe approach.
*/
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {


        // fetch plugins from google maven (https://maven.google.com)
        google() {
            content {
                includeGroupByRegex("androidx\\..*")
                includeGroupByRegex("com\\.android(\\..*|)")
                includeGroupByRegex("com\\.google\\.android\\..*")
                includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
                /*
                This includes Google Play Services packages starting with com.google.gms,
                with the possibility of either sub-packages or just com.google.gms.
                 */
                includeGroupByRegex("com\\.google\\.gms(\\..*|)")
                /*
                This includes Google ML Kit packages starting with com.google.mlkit,
                which is used for machine learning tasks in Android apps.
                 */
                includeGroupByRegex("com\\.google\\.mlkit")
                /*
                This includes the Oboe library for high-performance audio,
                 starting with com.google.oboe.
                 */
                includeGroupByRegex("com\\.google\\.oboe")
                /*
                This includes the Prefab library, which is a packaging solution for C/C++
                 dependencies, starting with com.google.prefab.
                 */
                includeGroupByRegex("com\\.google\\.prefab")
                includeGroupByRegex("com\\.google\\.testing\\.platform")
            }

            /*
                 * This block allows you to specify which types of artifacts (releases or snapshots)
                 * should be retrieved from a particular Maven repository.
                 * */
            mavenContent {
                releasesOnly()
            }
        }


        // fetch dagger plugin only
        mavenCentral() {
            content {
                includeGroup("com.google.dagger")
                includeGroup("com.google.dagger.hilt.android")
            }
            mavenContent {
                releasesOnly()
            }
        }

        // fetch plugins from gradle plugin portal (https://plugins.gradle.org)
        gradlePluginPortal()

        /*
        * Sonatype hosts public repositories like the Sonatype OSS Repository, which is used
        * by developers to publish snapshots and releases of their software.
        */
        // fetch snapshot plugins from sonatype
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}
/*
* This block is used to manage dependency resolution settings, including configuring repositories, dependency versions, and resolution strategies.
* It helps maintain consistency across multiple modules in a project.
* */

dependencyResolutionManagement {
    /*
    * set(RepositoriesMode.FAIL_ON_PROJECT_REPOS): This sets the mode to FAIL_ON_PROJECT_REPOS,
    * which means that if any subproject (or module) tries to define its own repositories,
    *  the build will fail.
    * */
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    /*
    * Why Use FAIL_ON_PROJECT_REPOS?
Consistency: By enforcing this setting, you ensure that all modules use the same repositories
* defined at the root level, promoting consistency across the project.
Centralized Management: This approach simplifies dependency management,
*  making it easier to manage and update repositories in one place rather
* than having them scattered across multiple modules.
Avoid Conflicts: It helps prevent potential conflicts or issues that might arise from
* different modules referencing different repositories.
    * */
    repositories {
        // fetch libraries from google maven (https://maven.google.com)
        google() {
            content {
                includeGroupByRegex("androidx\\..*")
                includeGroupByRegex("com\\.android(\\..*|)")
                includeGroupByRegex("com\\.google\\.android\\..*")
                includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
                includeGroupByRegex("com\\.google\\.gms(\\..*|)")
                includeGroupByRegex("com\\.google\\.mlkit")
                includeGroupByRegex("com\\.google\\.oboe")
                includeGroupByRegex("com\\.google\\.prefab")
                includeGroupByRegex("com\\.google\\.testing\\.platform")
            }
            mavenContent {
                releasesOnly()
            }
        }

        // fetch libraries from maven central
        mavenCentral() {
            mavenContent {
                releasesOnly()
            }
        }

        // fetch snapshot libraries from sonatype
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}
rootProject.name = "pokedex-compose"
include(":app")
//TODO uncomment after adding modules & features
include(":core:model")
include(":core:network")
//include(":core:viewmodel")
//include(":core:database")
//include(":core:data")
include(":core:test")
//include(":core:navigation")
//include(":core:designsystem")
//include(":core:preview")
//
//include(":feature:home")
//include(":feature:details")
 