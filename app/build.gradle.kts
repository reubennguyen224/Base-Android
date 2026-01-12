import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.devtoolsKsp)
    id("kotlin-parcelize")
}

android {
    namespace = "com.baseapp"
    compileSdk = Versions.Android.COMPILE_SDK

    defaultConfig {
        applicationId = "com.baseapp"
        minSdk = Versions.Android.MIN_SDK
        targetSdk = Versions.Android.TARGET_SDK
        versionCode = Versions.App.VERSION_CODE
        versionName = Versions.App.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    var appName = ""
    applicationVariants.all {
        outputs.all {
            (this as? com.android.build.gradle.internal.api.BaseVariantOutputImpl)?.apply {
                val date = SimpleDateFormat("yyyyMMdd").format(Date())
                val buildType = buildType.name
                val flavor = productFlavors[0].name
                outputFileName = "${appName}_${date}_${flavor}_${buildType}_${versionName}.apk"
            }
        }
    }

    flavorDimensions += "baseapp"
    productFlavors {
        create("dev") {
            dimension = "baseapp"
            applicationId = Configs.Dev.APPLICATION_ID
            appName = Configs.Dev.APPLICATION_NAME
            resValue("string", "app_name", Configs.Dev.APPLICATION_NAME)

            addManifestPlaceholders(
                mapOf(
                    "app_name" to Configs.Dev.APPLICATION_NAME
                )
            )
        }

        create("product") {
            dimension = "baseapp"
            applicationId = Configs.RELEASE.APPLICATION_ID
            appName = Configs.RELEASE.APPLICATION_NAME
            resValue("string", "app_name", Configs.RELEASE.APPLICATION_NAME)

            addManifestPlaceholders(
                mapOf(
                    "app_name" to Configs.RELEASE.APPLICATION_NAME
                )
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.kotlinx.serialization)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.media)

    //view
    implementation(libs.material)
    implementation(libs.androidx.swiperefresh)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.splash)

    //Coroutines
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    //Glide
    implementation(libs.glide)
    ksp(libs.glideKsp)

    //Navigation
    implementation(libs.androidx.nav.ui)
    implementation(libs.androidx.nav.fragment)
    implementation(libs.androidx.nav.dynamic)

    //DI
    implementation(libs.koin.android)
    implementation(libs.koin.annotation)
    ksp(libs.koin.compiler)

    //Networking
    implementation(platform(libs.okhttpBom))
    implementation(libs.okhttp)
    implementation(libs.retrofit2)

    //Log
    implementation(libs.timber)

    //Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}