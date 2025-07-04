plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.junit)
    alias(libs.plugins.google.dagger.hilt.android)

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

        resourceConfigurations += listOf("en_US", "ru_RU")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
//            storeFile = file(getKeystoreFile("STORE_FILE_BS64"))
//            storePassword = System.getenv("STORE_PASSWORD")
//            keyAlias = System.getenv("KET_ALIAS")
//            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-delete-logs.pro",
            )

            ndk { debugSymbolLevel = null }
        }

        debug {
            isDebuggable = true
            isJniDebuggable = true

            ndk { debugSymbolLevel = "FULL" }
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

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.android.image.cropper)

    implementation(libs.androidx.graphics.shapes)

    implementation(libs.kotlinx.serialization)

    kapt(libs.hilt.compiler) // 09.2024 - Hilt не поддерживает ksp
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.accompanist.permissions)

    implementation(libs.compose.qr.code)

    implementation(libs.core.ktx)
    implementation(libs.kotlin.reflect)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.core.ktx)

    implementation(libs.coil.compose)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material)

    implementation(libs.cloudy) // Blur для <= 31 API

    androidTestImplementation(libs.androidx.ui.test.android)
    androidTestImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

junitPlatform {
    instrumentationTests.includeExtensions.set(true)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

//private fun getKeystoreFile(envName: String): File {
//    val keystoreFile = File(rootProject.projectDir, "keystore.jks")
//    if (!keystoreFile.exists()) {
//        val keystoreContent = Base64.getDecoder().decode(System.getenv(envName))
//
//        keystoreContent?.let {
//            keystoreFile.writeBytes(it)
//        }
//    }
//
//    return keystoreFile
//}
