import java.io.File
import java.util.Base64

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.arturbosch.detekt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.android)

    kotlin("kapt")
}

android {
    namespace = "com.cryptoemergency.cryptoemergency"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cryptoemergency.cryptoemergency"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file(getKeystoreFile())
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            ndk { debugSymbolLevel = "NONE" }

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
            isDebuggable = true
            isJniDebuggable = true

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

    implementation(libs.androidx.graphics.shapes) // Либа для рисования shapes

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler) // Компилятор аннотаций Room
    implementation(libs.androidx.room.ktx) // Room - Дополнительно для Coroutines, Flows

    implementation(libs.androidx.datastore.preferences) // datastore preferences
    implementation(libs.androidx.datastore) // datastore-proto preferences

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android) // hilt common
    kapt(libs.hilt.compiler) // Компилятор аннотация hilt. 2024 - Hilt не поддерживает ksp

    implementation(libs.ktor.client.core) // Ktor Client core dependency
    implementation(libs.ktor.client.okhttp) // Ktor Client engine dependency
    implementation(libs.ktor.client.serialization) // Ktor Client JSON feature
    implementation(libs.ktor.client.content.negotiation) // Ktor serialization to JSON
    implementation(libs.ktor.serialization.kotlinx.json) // Ktor serialization to JSON
    implementation(libs.ktor.client.logging) // Ktor Client logging

    implementation(libs.slf4j.api) // логирование
    implementation(libs.logback.classic) // логирование

    implementation(libs.compose.qr.code) // QR code

    implementation(libs.core.ktx)

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
    implementation(libs.androidx.compose.material)

    detektPlugins(libs.detekt.formatting)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

ksp {
    arg("room.schemaLocation", "$projectDir/repository/database/schemas")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

detekt {
    toolVersion = "1.23.3"
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

tasks.detekt {
    reports {
        html.required.set(true)
        md.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
        sarif.required.set(false)
    }
}

fun getKeystoreFile(): File {
    val keystoreFile = File(rootProject.projectDir, "keystore.jks")
    if (!keystoreFile.exists()) {
        val keystoreContent = System.getenv("KEYSTORE_FILE")?.let {
            Base64.getDecoder().decode(it)
        }
        keystoreContent?.let {
            keystoreFile.writeBytes(it)
        }
    }

    return keystoreFile
}
