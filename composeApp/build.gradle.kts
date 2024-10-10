plugins {
    id(
        libs.plugins.android
            .get()
            .pluginId,
    )
    id(
        libs.plugins.kotlinMultiplatform
            .get()
            .pluginId,
    )
    id(
        libs.plugins.compose
            .get()
            .pluginId,
    )
    id(
        libs.plugins.cocoapods
            .get()
            .pluginId,
    )
    id(
        libs.plugins.serialization
            .get()
            .pluginId,
    )
}

version = "0.0.1"

kotlin {
    jvmToolchain(17)
    androidTarget()

    listOf(
        iosArm64(),
        iosX64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
        }
    }

    cocoapods {
        summary = "Salon iOS SDK"
        homepage = "https://google.com"
        ios.deploymentTarget = "15.0"

        framework {
            transitiveExport = false
            baseName = "SharedSDK"
        }
    }

    configurations.all {
        exclude(group = "androidx.annotation", module = "androidx.annotation:annotation-jvm")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(libs.compose.viewmodel)
            implementation(libs.compose.navigation)

            implementation(compose.components.resources)

            api(libs.kotlinx.serialization.core)
            api(libs.kotlinx.coroutines)

            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.negotiation)
            implementation(libs.ktor.client.logging)

            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)

            implementation(libs.multiplatform.settings.core)
            implementation(libs.multiplatform.settings.no.arg)

            implementation(libs.kodein.di)
            implementation(libs.kermit.logger)

        }

        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)
            implementation(libs.accompanist.permissions)
            implementation(libs.androidx.recyclerview)
            implementation(libs.zxing)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "ru.example.composeui"
    compileSdk = 34
    defaultConfig {
        applicationId = "ru.example.composeui"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
    }
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src/androidMain/java", "src/dev/java")
            }
        }
    }
}
