import com.zywczas.buildutils.Versions

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.detekt)
}

android {
  namespace = "com.zywczas.featureguestslist"
  compileSdk = Versions.COMPILE_SDK

  defaultConfig {
    minSdk = Versions.MIN_SDK
  }

  buildTypes {
    release {
      isMinifyEnabled = false
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
  implementation(project(":network_guests"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui)
  implementation(libs.androidx.material3)
  debugImplementation(libs.androidx.ui.tooling.preview)
  debugImplementation(libs.androidx.ui.tooling)

  implementation(platform(libs.koin.bom))
  implementation(libs.koin.core)
  implementation(libs.koin.compose.viewmodel)
}
