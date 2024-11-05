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
    alias(libs.plugins.androidx.junit)

    kotlin("kapt")
}

android {
    namespace = "com.cryptoemergency.cryptoemergency"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.testwork.geoapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        resourceConfigurations += listOf("en_US", "ru_RU")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file(getKeystoreFile())
            storePassword = "1k1aybW1jf2x$1hp"
            keyAlias = "main"
            keyPassword = "1k1aybW1jf2x$1hp"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-rules-without-logs.pro",
            )

            ndk { debugSymbolLevel = null }

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
            isDebuggable = true
            isJniDebuggable = true

            ndk { debugSymbolLevel = "FULL" }

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

        create("releaseWithLogs") {
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = true

            buildConfigField(
                "String",
                "PROTOCOL",
                "\"${project.properties["prod.server.protocol"]}\"",
            )
            buildConfigField(
                "String",
                "HOST",
                "\"${project.properties["prod.server.host"]}\"",
            )
            buildConfigField(
                "int",
                "PORT",
                project.properties["prod.server.port"].toString(),
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

    implementation(libs.android.image.cropper) // Image editor

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

    implementation(libs.accompanist.permissions)

    implementation(libs.compose.qr.code) // QR код

    implementation(libs.core.ktx)
    implementation(libs.kotlin.reflect)

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

    implementation(libs.cloudy) // Blur для <= 31 API

    detektPlugins(libs.detekt.formatting)

    androidTestImplementation(libs.androidx.ui.test.android) // Compose test framework
    androidTestImplementation(libs.junit.jupiter.api) // Instrumentation Test Support
    testImplementation(libs.junit.jupiter.api) // (Required) Writing and executing Unit Tests on the JUnit Platform
    testRuntimeOnly(libs.junit.jupiter.engine) // (Required) Writing and executing Unit Tests on the JUnit Platform
}

junitPlatform {
    instrumentationTests.includeExtensions.set(true)
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

private fun getKeystoreFile(): File {
    val keystoreFile = File(rootProject.projectDir, "keystore.jks")
    if (!keystoreFile.exists()) {
        val keystoreContent = Base64.getDecoder().decode("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")

        keystoreContent?.let {
            keystoreFile.writeBytes(it)
        }
    }

    return keystoreFile
}
