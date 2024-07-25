plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "uz.ibrohim.food"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.ibrohim.food"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation Fragment
    implementation (libs.androidx.fragment)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigationUi)

    //Glide
    annotationProcessor(libs.glide.compiler)
    implementation(libs.glide.bumptech)

    //ViewModel
    implementation(libs.viewModel.lifecycle)
    implementation(libs.viewModel.runtime)

    //Retrofit
    implementation(libs.retrofit.squareup)
    implementation(libs.retrofit.converter)
    implementation(libs.squareup.okhttp3)

    //CircleImage
    implementation(libs.circleimageview.hdodenhof)

    //Toast
    implementation (libs.toast.grender)
}