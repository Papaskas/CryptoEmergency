import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.compose.compiler)

    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.cryptoemergency.cryptoemergency"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cryptoemergency.cryptoemergency"
        minSdk = 21
        //noinspection EditedTargetSdkVersion
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

//    signingConfigs {
//        create("release") {
//            keyAlias = ""
//            storeFile = file("")
//            storePassword = ""
//            keyPassword = ""
//        }
//    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            // signingConfig = signingConfigs.getByName("release")

            ndk { debugSymbolLevel = "FULL" }

            buildConfigField(
                "String",
                "PROTOCOL",
                "\"${project.properties["prod.server.protocol"]}\""
            )
            buildConfigField(
                "String",
                "HOST",
                "\"${project.properties["prod.server.host"]}\""
            )
            buildConfigField(
                "int",
                "PORT",
                project.properties["prod.server.port"].toString()
            )
        }

        debug {
            isMinifyEnabled = false

            ndk { debugSymbolLevel = "SYMBOL_TABLE" }

            buildConfigField(
                "String",
                "PROTOCOL",
                "\"${project.properties["dev.server.protocol"]}\"",
            )
            buildConfigField(
                "String",
                "HOST",
                "\"${project.properties["dev.server.host"]}\"",
            )
            buildConfigField(
                "int",
                "PORT",
                project.properties["dev.server.port"].toString(),
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/INDEX.LIST"
        }
    }
}

dependencies {

    implementation(libs.androidx.datastore.preferences) // datastore preferences
    implementation(libs.androidx.datastore) // datastore-proto preferences

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.core.ktx) // hilt common

    implementation(libs.ktor.client.core) // Ktor Client core dependency
    implementation(libs.ktor.client.okhttp) // Ktor Client engine dependency
    implementation(libs.ktor.client.serialization) // Ktor Client JSON feature
    implementation(libs.ktor.client.content.negotiation) // Ktor serialization to JSON
    implementation(libs.ktor.serialization.kotlinx.json) // Ktor serialization to JSON
    implementation(libs.ktor.client.logging) // Ktor Client logging

    implementation(libs.slf4j.api) // логирование
    implementation(libs.logback.classic) // логирование

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.core.ktx)

    implementation(libs.coil.compose) // для асинхронной загрузки изображений

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)

    detektPlugins(libs.detekt.formatting)
    kapt(libs.hilt.compiler) // Компилятор аннотация hilt

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}

detekt {
    toolVersion = "1.23.3"
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
