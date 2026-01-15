import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tech.data"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://jsonplaceholder.typicode.com\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://jsonplaceholder.typicode.com/\""
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
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // ------------------------------------------------------
    // Core Android
    // ------------------------------------------------------
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // ------------------------------------------------------
    // Serialization
    // ------------------------------------------------------
    implementation(libs.kotlinx.serialization.json)

    // ------------------------------------------------------
    // Room
    // ------------------------------------------------------
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    // ------------------------------------------------------
    // Hilt
    // ------------------------------------------------------
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ------------------------------------------------------
    // Ktor Client
    // ------------------------------------------------------
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.json)

    // ------------------------------------------------------
    // AndroidX Test Utils (si se usan dentro del módulo)
    // ------------------------------------------------------
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)

    // ------------------------------------------------------
    // Unit Tests
    // ------------------------------------------------------
    testImplementation(libs.junit)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.androidx.room.testing)

    // ------------------------------------------------------
    // Modules
    // ------------------------------------------------------
    implementation(project(":core"))
}