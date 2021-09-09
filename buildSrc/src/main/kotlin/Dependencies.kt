import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

  const val MULTIDEX = "androidx.multidex:multidex:${Versions.MULTIDEX}"
  const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
  const val GSON = "com.google.code.gson:gson${Versions.GSON}"

  const val DUAL_CACHE = "com.vincentbrison.openlibraries.android:dualcache:${Versions.DUAL_CACHE}"

  const val DEXTER = "com.karumi:dexter:${Versions.DEXTER}"

  const val RESULT = "com.github.kittinunf.result:result:3.0.0"

  object AndroidX {
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}"
    const val CORE = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
    const val MATERIAL = "com.google.android.material:material:${Versions.AndroidX.DESIGN}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}"
    const val SWIPE_REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.SWIPE_REFRESH}"
  }

  object Lifecycle {
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.ARCHITECTURE_COMPONENT}"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata:${Versions.AndroidX.ARCHITECTURE_COMPONENT}"
    const val COMPILER = "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidX.ARCHITECTURE_COMPONENT}"
    const val JAVA8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.ARCHITECTURE_COMPONENT}"
    const val STREAMS = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.AndroidX.ARCHITECTURE_COMPONENT}"
    const val TESTING = "androidx.arch.core:core-testing:${Versions.AndroidX.ARCHITECTURE_COMPONENT}"
    const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.LIFECYCLE_EXTENSIONS}"

  }


  object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val RX = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}"
  }

  object OkHttp {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val TLS = "com.squareup.okhttp3:okhttp-tls:${Versions.OKHTTP}"
    const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
  }

  object Rx {
    const val JAVA = "io.reactivex.rxjava2:rxjava:${Versions.RxLibrary.RXJAVA2}"
    const val ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RxLibrary.RXANDROID2}"
  }

  object Koin {
    const val CORE = "org.koin:koin-android:${Versions.KOIN}"
    const val SCOPE = "org.koin:koin-android-scope:${Versions.KOIN}"
    const val VIEW_MODEL = "org.koin:koin-android-viewmodel:${Versions.KOIN}"
  }

  object UI {
    const val SHAPE_OF_VIEW = "com.github.florent37:shapeofview:${Versions.UI.SHAPE_OF_VIEW}"
    const val PINNED_SECTION = "com.oushangfeng:PinnedSectionItemDecoration:${Versions.UI.PINNED_SECTION}"
    const val ALERTER = "com.tapadoo.android:alerter:${Versions.UI.ALERTER}"

    //
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.UI.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.UI.GLIDE}"

    //
    const val IMAGE_CROPPER = "com.theartofdev.edmodo:android-image-cropper:${Versions.IMAGE_CROPPER}"
    const val PHOTO_VIEW = "com.github.chrisbanes:PhotoView:2.0.0"
  }

  object Airbnb {
    const val EPOXY = "com.airbnb.android:epoxy:${Versions.Airbnb.EPOXY}"
    const val EPOXY_PROCESSOR = "com.airbnb.android:epoxy-processor:${Versions.Airbnb.EPOXY}"
    const val MAVERICKS = "com.airbnb.android:mvrx:${Versions.Airbnb.MAVERICKS}"
    // const val MAVERICKS = "com.airbnb.android:mavericks:${Versions.Airbnb.MAVERICKS}"
  }
}

fun DependencyHandler.epoxy() {
  implementation(Dependencies.Airbnb.EPOXY)
  kapt(Dependencies.Airbnb.EPOXY_PROCESSOR)
}

fun DependencyHandler.appCompat() {
  implementation(Dependencies.AndroidX.APP_COMPAT)
  implementation(Dependencies.AndroidX.CORE)
  implementation(Dependencies.AndroidX.MATERIAL)
  implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
  implementation(Dependencies.AndroidX.SWIPE_REFRESH)
}

fun DependencyHandler.lifecycle() {
  implementation(Dependencies.Lifecycle.VIEW_MODEL)
  implementation(Dependencies.Lifecycle.LIVE_DATA)
  implementation(Dependencies.Lifecycle.EXTENSIONS)
  implementation(Dependencies.Lifecycle.STREAMS)
  implementation(Dependencies.Lifecycle.JAVA8)
  kapt(Dependencies.Lifecycle.COMPILER)
}

fun DependencyHandler.retrofit() {
  implementation(Dependencies.Retrofit.RETROFIT)
  implementation(Dependencies.Retrofit.GSON)
  implementation(Dependencies.Retrofit.RX)
}

fun DependencyHandler.okHttp() {
  implementation(Dependencies.OkHttp.OKHTTP)
  implementation(Dependencies.OkHttp.TLS)
  implementation(Dependencies.OkHttp.LOGGING)
}

fun DependencyHandler.rx() {
  implementation(Dependencies.Rx.JAVA)
  implementation(Dependencies.Rx.ANDROID)
}

fun DependencyHandler.koin() {
  api(Dependencies.Koin.CORE)
  api(Dependencies.Koin.SCOPE)
  api(Dependencies.Koin.VIEW_MODEL)
}

fun DependencyHandler.ui() {
  implementation(Dependencies.UI.SHAPE_OF_VIEW)
  implementation(Dependencies.UI.ALERTER)
  //
  implementation(Dependencies.UI.GLIDE)
  kapt(Dependencies.UI.GLIDE_COMPILER)
  //
  api(Dependencies.UI.IMAGE_CROPPER)
  implementation(Dependencies.UI.PHOTO_VIEW)
}

fun DependencyHandler.implementation(depName: String) {
  add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
  add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
  add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
  add("api", depName)
}
