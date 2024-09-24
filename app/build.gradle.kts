import com.example.pokedex.Configuration
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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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