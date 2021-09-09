import extensions.buildConfigBooleanField
import extensions.buildConfigStringField

plugins {
  id("android-base-app")
  id("android-base")
}

val KEYSTORE_PASSWORD: String by project
val KEY_PASSWORD: String by project

android {

  defaultConfig {
    applicationId = "com.hb.f88.app"
  }

  signingConfigs {
    getByName("debug") {
      storeFile = file("../publish/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
    create("release") {
      storeFile = file("../publish/release.keystore")
      storePassword = KEYSTORE_PASSWORD
      keyAlias = "HBui"
      keyPassword = KEY_PASSWORD
    }
  }

  buildTypes {
    getByName("debug") {
      signingConfig = signingConfigs.getByName("debug")
      isDebuggable = true
      isMinifyEnabled = false
      isShrinkResources = false
    }
    getByName("release") {
      signingConfig = signingConfigs.getByName("release")
      isDebuggable = false
      isMinifyEnabled = false
      isShrinkResources = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  flavorDimensions(BuildProductDimensions.ENVIRONMENT)
  productFlavors {
    ProductFlavorDevelop.appCreate(this).apply {
      resValue("string", "app_name", "F88 Dev")
      buildConfigBooleanField("MOCK_DATA", false)
      buildConfigStringField("BASE_URL", "https://www.google.com/")
    }
    ProductFlavorProduction.appCreate(this).apply {
      resValue("string", "app_name", "F88")
      buildConfigBooleanField("MOCK_DATA", false)
      buildConfigStringField("BASE_URL", "https://www.google.com/")
    }
  }

//  androidExtensions {
//    isExperimental = true
//  }

  packagingOptions {
    exclude("META-INF/*.kotlin_module")
  }

  lintOptions {
    isCheckReleaseBuilds = false
    isAbortOnError = false
  }
}

dependencies {
  appCompat()
  lifecycle()
  rx()
  retrofit()
  okHttp()

  ui()
  epoxy()

  implementation(Dependencies.DEXTER)
  implementation(Dependencies.MULTIDEX)
  implementation(Dependencies.DUAL_CACHE)
  implementation(Dependencies.RESULT)

  implementation(project(BuildModules.Commons.BASE))
  implementation(project(BuildModules.Commons.UI))

  implementation("com.github.skydoves:powerspinner:1.1.9")

}
