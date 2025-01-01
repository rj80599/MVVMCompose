import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties
import java.util.TimeZone

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

val path = rootProject.file("gradle.properties")
val keyProperties = Properties()
keyProperties.load(FileInputStream(path))


android {
    namespace = "com.example.mvvmcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mvvmcompose"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        setProperty("archivesBaseName", "MV-$versionName.$versionCode-" + buildTime())
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "version"
    productFlavors {
        create("free") {
            dimension = "version"
            versionNameSuffix = "-free"
        }

        create ("paid") {
            dimension = "version"
        }

    }

    signingConfigs {
        getByName("debug"){
            storeFile = file(keyProperties["storeFile"] as String)
            keyPassword = keyProperties["keyPassword"] as String
            storePassword = keyProperties["storePassword"] as String
            keyAlias = keyProperties["keyAlias"] as String
        }
        create("release"){
            storeFile = file(keyProperties["storeFile"] as String)
            keyPassword = keyProperties["keyPassword"] as String
            storePassword = keyProperties["storePassword"] as String
            keyAlias = keyProperties["keyAlias"] as String

        }
        create("staging"){
            storeFile = file(keyProperties["storeFile"] as String)
            keyPassword = keyProperties["keyPassword"] as String
            storePassword = keyProperties["storePassword"] as String
            keyAlias = keyProperties["keyAlias"] as String
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs["release"]
            buildConfigField("String","base_url","\"https://api.jsonbin.io/v3/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs["debug"]
            buildConfigField("String","base_url","\"https://api.jsonbin.io/v3/\"")
            versionNameSuffix = "-debug"
//            setProperty("archivesBaseName", "MV$versionNameSuffix-" + buildTime())
        }
        create("staging") {
            signingConfig = signingConfigs["staging"]
            buildConfigField("String","base_url","\"https://api.jsonbin.io/v3/\"")
            versionNameSuffix = "-staging"
            isMinifyEnabled = true
            isShrinkResources = true
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
    kotlinOptions {
        jvmTarget = "11"
    }


    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //livedata
    implementation(libs.androidx.runtime.livedata)
    // image handling
//    implementation(libs.coil.compose)

    //lifecycle viewmodel
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    // coroutine
//    implementation(libs.kotlinx.coroutines.android)

    //navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    //auto
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

fun buildTime(): String{
    val df = SimpleDateFormat("dd_MMM_yyyy_HHmm")
    df.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
    return df.format(Date())
}