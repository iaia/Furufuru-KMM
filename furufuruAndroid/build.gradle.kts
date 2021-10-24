plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")

    // ViewModel
    val lifecycle_version = "2.3.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    val compose = "1.0.4"
    implementation("androidx.compose.ui:ui:$compose")
    // Tooling support (Previews, etc.)
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:$compose")
    // Material Design
    implementation("androidx.compose.material:material:$compose")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:$compose")
    implementation("androidx.compose.material:material-icons-extended:$compose")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:$compose")
    implementation("androidx.compose.runtime:runtime-rxjava2:$compose")

    // koin
    val koin_version = "3.1.2"
    // Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:$koin_version")
    // Koin Java Compatibility
    implementation("io.insert-koin:koin-android-compat:$koin_version")
    // Koin for Jetpack WorkManager
    implementation("io.insert-koin:koin-androidx-workmanager:$koin_version")
    // Koin for Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:$koin_version")

}

android {
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(31)
        // versionCode = 1
        // versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.5.31"
        kotlinCompilerExtensionVersion = "1.0.4"
    }
}
