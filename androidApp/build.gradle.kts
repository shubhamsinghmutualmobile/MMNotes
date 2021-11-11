plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.5.31-1.0.0"
}

dependencies {
    val composeVersion = "1.0.5"

    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.4.0")
    // Compose Material Design
    implementation("androidx.compose.material:material:$composeVersion")
    // Animations
    implementation("androidx.compose.animation:animation:$composeVersion")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // Navigation
    implementation("io.github.raamcosta.compose-destinations:core:0.9.4-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:0.9.4-beta")
    implementation("androidx.navigation:navigation-compose:2.4.0-beta02")

    implementation("io.insert-koin:koin-android:3.1.3")
    implementation("io.insert-koin:koin-androidx-compose:3.1.3")
}

android {
    compileSdk = 31
    sourceSets["debug"].java.srcDir(file("build/generated/ksp/debug/kotlin"))
    defaultConfig {
        applicationId = "com.mutualmobile.mmnotes.androidApp"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
}
