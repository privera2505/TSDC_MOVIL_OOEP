plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.vinilo.view"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vinilo.view"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.espresso.contrib)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack ViewModel & LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Retrofit
    implementation (libs.retrofit)
    // Converter JSON (Gson)
    implementation (libs.converter.gson)
    // Coroutines (para llamadas asincrónicas si usas ViewModel)
    implementation (libs.kotlinx.coroutines.android)
    // Logging (opcional pero útil para debug)
    implementation (libs.logging.interceptor)

    implementation(libs.glide)

    // Espresso core
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(libs.androidx.fragment.testing)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // JUnit para pruebas Android
    androidTestImplementation(libs.androidx.junit.v115)

    // Reglas de prueba
    androidTestImplementation(libs.androidx.rules)

    // Opcionales
    androidTestImplementation(libs.androidx.espresso.intents)
    androidTestImplementation(libs.androidx.espresso.contrib)

    // Dependencias normales de test (unitarias)
    testImplementation(libs.junit)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.room.ktx)


    // Hilt
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-compiler:2.48")

    //Firebase
    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

    // Firebase Performance (sin versión gracias al BoM)
    implementation("com.google.firebase:firebase-perf-ktx")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

    configurations.all {
        exclude(group = "com.google.protobuf", module = "protobuf-lite")
    }

}