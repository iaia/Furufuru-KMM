import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.31"
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                val kotlin_version = "1.5.31"
                // coroutine
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

                implementation("com.google.android.material:material:1.4.0")

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

                // kotlin-serialization
                implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-properties:1.0.1")
                implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.6.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(31)
    }
}
