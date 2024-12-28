plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.papaska.data"
    compileSdk = 34

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))

    ksp(libs.androidx.room.compiler)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.proto.datastore)

    implementation(libs.kotlinx.serialization)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.resources)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    testImplementation(libs.ktor.client.mock)

    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}

ksp {
    arg("room.schemaLocation", "$projectDir/repository/database/schemas")
}
