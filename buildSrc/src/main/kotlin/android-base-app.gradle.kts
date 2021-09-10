plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
}

android {
  buildFeatures.viewBinding = true

  compileSdkVersion(Versions.TARGET_ANDROID_SDK)
  defaultConfig {
    minSdkVersion(Versions.MIN_ANDROID_SDK)
    targetSdkVersion(Versions.TARGET_ANDROID_SDK)
    versionCode = Versions.getVersionCode()
    versionName = Versions.VERSION_NAME

    consumerProguardFiles("consumer-rules.pro")

    multiDexEnabled = true
    vectorDrawables.useSupportLibrary = true

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

  kapt {
    correctErrorTypes = true
  }
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
