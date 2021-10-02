
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Versions {

  const val MIN_ANDROID_SDK = 21
  const val TARGET_ANDROID_SDK = 30

  fun getVersionCode(): Int {
    val date = Date()
    val formatter = SimpleDateFormat("yyMMddHHmm", Locale.getDefault())
    return (formatter.format(date).toDouble() - 2000000000).toInt()
  }

  const val VERSION_NAME = "1.0.13"

  const val MULTIDEX = "2.0.1"


  object AndroidX {
    const val APP_COMPAT = "1.3.1"
    const val CORE_KTX = "1.6.0"
    const val ARCHITECTURE_COMPONENT = "2.3.0"
    const val LIFECYCLE_EXTENSIONS = "2.2.0"
    const val CONSTRAINT_LAYOUT = "2.1.0"
    const val DESIGN = "1.4.0"
    const val SWIPE_REFRESH = "1.1.0"
    const val ROOM = "2.3.0"
  }

  object Firebase {
    const val CORE = "17.2.2"
    const val ANALYTICS = "17.6.0"
    const val MESSAGING = "20.3.0"
    const val CRASHLYTICS = "18.2.0" //"17.0.0-beta04"
  }

  object RxLibrary {
    const val RXJAVA2 = "2.2.17"
    const val RXANDROID2 = "2.1.1"
  }

  object UI {
    const val SHAPE_OF_VIEW = "1.3.2"
    const val RECYCLER_HELPER = "3.0.4"
    const val PINNED_SECTION = "1.3.2-androidx"
    const val ALERTER = "3.0.1"
    const val GLIDE = "4.11.0"
  }

  object Airbnb {
    const val EPOXY = "4.6.2"
    // const val MAVERICKS = "2.3.0"
    const val MAVERICKS = "1.5.1"
  }

  const val TIMBER = "4.5.1"
  const val KOIN = "2.0.1"
  const val OKHTTP = "4.4.0"
  const val RETROFIT = "2.7.1"
  const val GSON = "2.8.6"
  const val DUAL_CACHE = "3.1.1"
  const val DEXTER = "6.1.2"
  const val IMAGE_CROPPER = "2.8.0"

}
