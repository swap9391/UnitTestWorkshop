plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.sj.unittestworkshop"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sj.unittestworkshop"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        viewBinding = true
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)


    testImplementation(libs.junit.junit) // JUnit for Unit Testing
    androidTestImplementation(libs.androidx.core)  // Core library for Android instrumentation tests
    androidTestImplementation(libs.androidx.junit) // JUnit extension library for Android instrumentation tests
    androidTestImplementation(libs.core.ktx)  // Kotlin extensions for Android instrumentation tests
    androidTestImplementation(libs.androidx.runner) // Android Test Runner for instrumentation tests
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose UI testing library

    //Espresso
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.intents)


    //Compose Dependencies
    val composeBom = libs.androidx.compose.bom
    implementation(composeBom)
    androidTestImplementation(composeBom)
    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Navigation Compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))


//implementation (libs.androidx.hilt.navigation.compose)
    // Optional - Integration with ViewModels
    //implementation (libs.androidx.lifecycle.viewmodel.compose)
    // Optional - Integration with LiveData
    //  implementation (libs.androidx.runtime.livedata)

}