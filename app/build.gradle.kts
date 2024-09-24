import com.example.pokedex.Configuration
import java.io.FileInputStream
import java.util.Properties

plugins {
//    TODO like skydoves
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" //TODO like skydoves
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}