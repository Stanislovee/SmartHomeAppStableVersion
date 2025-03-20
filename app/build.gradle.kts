plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.1.10"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.smarthome"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.smarthome"
        minSdk = 29
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("com.auth0:java-jwt:4.4.0")
    implementation("io.ktor:ktor-client-core:2.3.8")
    implementation("io.ktor:ktor-client-cio:2.3.8")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.8")
    implementation("io.ktor:ktor-serialization-gson:2.3.8")
    implementation("com.google.code.gson:gson:2.10.1")

    //implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    //implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("androidx.core:core-splashscreen:1.0.1")
   // implementation(libs.kotlinx.serialization.json)
   // implementation(libs.ktor.content)
  //  implementation(libs.ktor.json)
   // implementation(libs.ktor.logging)
   // implementation(libs.ktor.serialization.json)
    implementation (libs.retrofit)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
   // implementation(libs.ktor.client.core)
   // implementation(libs.ktor.client.cio)
   // implementation(libs.ktor.client.android)

    implementation(libs.firebase.auth)
//    implementation(libs.firebase.analytics)
//    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.ui.auth)
    implementation(libs.playservices.coroutines)
}