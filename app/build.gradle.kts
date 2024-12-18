import com.example.pokedex.Configuration
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("example.pokedex.android.application")
    id("example.pokedex.android.application.compose")
    id("example.pokedex.android.hilt")
    id("example.pokedex.spotless")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    /*
    * Introduced in Gradle Plugin 7.0.0 to replace the applicationId or
    * package declaration in the AndroidManifest.xml
    * Refers to the package or the namespace where your app’s source code is located
    *  If you have different modules in your app (like a feature module or a library module),
    *  each module can have its own namespace,
    * keeping code organization and build tasks modular and separate.
    * */
    namespace = "com.example.pokedex"
    compileSdk = 34

    defaultConfig {
        /*
        * Cannot be changed once your app is published without creating a new app in the Play Store.
        * */
        applicationId = "com.example.pokedex"
        minSdk = 28
        targetSdk = 34
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
        testInstrumentationRunner = "com.example.pokedex.compose.AndroidJUnitRunner"
    }

    signingConfigs {
        val properties = Properties()
        val localPropertyFile = project.rootProject.file("local.properties")
        if (localPropertyFile.canRead()) {
            properties.load(FileInputStream("$rootDir/local.properties"))
        }
        create("release") {
            storeFile = file(properties["RELEASE_KEYSTORE_PATH"] ?: "../keystores/pokedex.jks")
            keyAlias = properties["RELEASE_KEY_ALIAS"].toString()
            keyPassword = properties["RELEASE_KEY_PASSWORD"].toString()
            storePassword = properties["RELEASE_KEYSTORE_PASSWORD"].toString()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }

        /*
* The kotlinOptions block in your build.gradle file is used to configure
* the behavior of the Kotlin compiler when compiling your Android project.
* Specifically, the freeCompilerArgs property allows you to pass additional compiler
* arguments to control how Kotlin code is compiled.
* */
        kotlinOptions {
            freeCompilerArgs += listOf(
                "-Xno-param-assertions",    // Disables parameter nullability assertions at runtime for better performance.
                "-Xno-call-assertions",     //  Disables call-site nullability checks for non-nullable return types.
                "-Xno-receiver-assertions"  //  Disables receiver nullability checks for extension functions or properties.
            )
        }

        /*
        * The packaging block in your build.gradle file configures how the build system
        * handles resources during the packaging phase (when the APK or AAB is being built).
        *  In this specific case, the excludes list is used to exclude certain
        * files or directories from being included in the final APK/AAB.
        * */
        packaging {
            resources {
                excludes += listOf(
                    "DebugProbesKt.bin",               // Exclude Kotlin Coroutine debug file
                    "kotlin-tooling-metadata.json",    // Exclude Kotlin build metadata file
                    "kotlin/**",                       // Exclude all Kotlin-related resource files
                    "META-INF/*.version"               // Exclude version info in META-INF directory
                )
            }
        }
    }


    buildFeatures {
        buildConfig = true // Enable generation of BuildConfig class
    }

    hilt {
        /*
        * This setting enables Hilt's aggregating task, which allows for more efficient processing of
        * Hilt's dependency injection code generation.
        * When set to true, it instructs the Hilt compiler to aggregate dependencies
        * across multiple modules or components of your application.
        */
        enableAggregatingTask = true
    }

    kotlin {
//    The purpose here is to set up additional source directories for each source set(e.g., main, test, etc.)
        sourceSets.configureEach {
//      This line specifies that the Kotlin source files generated by KSP (Kotlin Symbol Processing)
            //      should be included in the Kotlin compilation process
            kotlin.srcDir(layout.buildDirectory.files("generated/ksp/$name/kotlin/"))
        }
        sourceSets.all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
    }

}

dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)


    // features
    //TODO uncomment
//    implementation(projects.feature.home)
//    implementation(projects.feature.details)

    // cores
    //TODO uncomment
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
//    implementation(projects.core.navigation)

    //TODO try replacing individual compose dependencies with bom compose

    // Provides support for integrating Jetpack Compose with the Android activity lifecycle
    implementation(libs.androidx.activity.compose)

    // Core library for building UIs with Jetpack Compose, including layout primitives and theming
    implementation(libs.androidx.compose.ui)

    // Essential for managing state and reactive programming in Jetpack Compose
    implementation(libs.androidx.compose.runtime)

    // Offers foundational components and utilities for creating rich UIs in Compose
    implementation(libs.androidx.compose.foundation)

    // di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)
    kspAndroidTest(libs.hilt.compiler)

    // baseline profile
    implementation(libs.profileinstaller)
    //TODO uncomment
//    baselineProfile(project(":baselineprofile"))

    // unit test

    // JUnit is a widely used testing framework for Java applications, providing annotations and assertions for unit tests
    testImplementation(libs.junit)

    // Turbine is a testing library for Kotlin coroutines that simplifies testing flow emissions
    testImplementation(libs.turbine)

    // Core testing utilities for Android, aiding in writing unit tests for Android components
    testImplementation(libs.androidx.test.core)

    // Mockito core for creating mock objects and defining their behavior in unit tests
    testImplementation(libs.mockito.core)

    // Mockito extension for Kotlin, providing a more idiomatic way to create and use mocks
    testImplementation(libs.mockito.kotlin)

    // Utilities for testing Kotlin coroutines, enabling control over coroutine execution and testing suspending functions
    testImplementation(libs.kotlinx.coroutines.test)

    // Truth assertion library for Java, offering a fluent API for writing expressive assertions in tests
    androidTestImplementation(libs.truth)

    // AndroidX JUnit support, providing additional functionalities for writing instrumented tests on Android
    androidTestImplementation(libs.androidx.junit)

    // Espresso framework for automated UI testing in Android, enabling interaction with UI components and assertions
    androidTestImplementation(libs.androidx.espresso)
//  androidTestImplementation(libs.android.test.runner)
}