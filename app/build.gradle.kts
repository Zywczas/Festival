import com.zywczas.buildutils.Versions

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.detekt)
}

android {
  namespace = "com.zywczas.festival"
  compileSdk = Versions.COMPILE_SDK

  defaultConfig {
    applicationId = "com.zywczas.festival"
    minSdk = Versions.MIN_SDK
    targetSdk = Versions.TARGET_SDK
    versionCode = Versions.VERSION_CODE
    versionName = Versions.VERSION_NAME
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = Versions.JAVA_VERSION
    targetCompatibility = Versions.JAVA_VERSION
  }
  kotlinOptions {
    jvmTarget = Versions.JVM_TARGET
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(project(":common_compose"))
  implementation(project(":common_util"))
  implementation(project(":feature_guests_list"))
  implementation(project(":network_caller"))
  implementation(project(":network_guests"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.activity.compose)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)

  implementation(libs.androidx.navigation.compose)

  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(libs.koin.androidx.compose)

  implementation(libs.kotlin.serialization.json)
}
