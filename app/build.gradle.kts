plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.sobiscanner"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.sobiscanner"
        minSdk = 26
        targetSdk = 33
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
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("com.google.android.material:material:1.10.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.jpardogo.googleprogressbar:library:1.2.0")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.8.1")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}