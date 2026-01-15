import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tech.core"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}

dependencies {
    // ------------------------------------------------------
    // Core Android
    // ------------------------------------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // ------------------------------------------------------
    // DataStore
    // ------------------------------------------------------
    implementation(libs.datastore.preferences)

    // ------------------------------------------------------
    // Serialization
    // ------------------------------------------------------
    implementation(libs.kotlinx.serialization.json)

    // ------------------------------------------------------
    // Hilt
    // ------------------------------------------------------
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    // Hilt Navigation (si este módulo lo usa)
    implementation(libs.hilt.navigation.compose)

    // ------------------------------------------------------
    // Ktor Client
    // ------------------------------------------------------
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.serialization.json)

    // ------------------------------------------------------
    // Room
    // ------------------------------------------------------
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    // ------------------------------------------------------
    // Tests
    // ------------------------------------------------------
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}