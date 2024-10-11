plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
//    alias(libs.plugins.google.ksp) // Apply the KSP plugin

}

android {
    namespace = "com.test.medmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.test.medmvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")





    implementation("com.google.dagger:dagger:2.48")
    kapt("com.google.dagger:dagger-compiler:2.48")


//    implementation(libs.dagger)               // Dagger
//    kapt(libs.dagger.compiler) // Dagger Compiler

    implementation(libs.androidx.lifecycle.viewmodel.ktx)  // Lifecycle
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.retrofit)             // Retrofit
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)  // OkHttp


    implementation(libs.androidx.room.runtime)   // Room
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.rxjava2)
    androidTestImplementation("androidx.room:room-testing:2.6.1")


    implementation(libs.kotlinx.coroutines.core)  // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.glide)                 // Glide

    implementation(libs.rxjava)                // RxJava
    implementation(libs.rxandroid)
    implementation(libs.retrofit.adapter.rxjava2)

    implementation(libs.androidx.recyclerview)  // RecyclerView
    implementation(libs.androidx.cardview)
    // CardView
    implementation(libs.swiperefreshlayout)      // Swipe To Refresh

    //Helpers/Random
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")
}