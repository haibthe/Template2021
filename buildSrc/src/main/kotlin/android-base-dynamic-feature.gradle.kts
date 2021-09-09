plugins {
  id("com.android.dynamic-feature")
  id("kotlin-android")
  id("kotlin-android-extensions")
  kotlin("android")
  kotlin("kapt")
}

android {

  buildFeatures.viewBinding = true

  compileSdkVersion(Versions.TARGET_ANDROID_SDK)
  defaultConfig {
    applicationId = "com.hb.vbd.df"
    minSdkVersion(Versions.MIN_ANDROID_SDK)
    targetSdkVersion(Versions.TARGET_ANDROID_SDK)
    versionCode = 1
    versionName = "1.0"

    vectorDrawables {
      useSupportLibrary = true
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    val options = this
    options.jvmTarget = "1.8"
  }

  flavorDimensions(BuildProductDimensions.ENVIRONMENT)
  productFlavors {
    ProductFlavorDevelop.libraryCreate(this)
//        ProductFlavorQA.libraryCreate(this)
    ProductFlavorProduction.libraryCreate(this)
  }

//  androidExtensions {
//    isExperimental = true
//  }

  kapt {
    correctErrorTypes = true
  }
}

dependencies {
  implementation(project(BuildModules.APP))
  implementation(project(BuildModules.Commons.BASE))
}

project.android.buildTypes.all {
  javaCompileOptions.annotationProcessorOptions.arguments(
    mapOf(
      "epoxyDisableDslMarker" to "true",
      "enableParallelEpoxyProcessing" to "true"
    )
  )
  println("HBui : ${javaCompileOptions}")
}
