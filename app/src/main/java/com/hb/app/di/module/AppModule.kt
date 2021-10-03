package com.hb.app.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.hb.app.data.cache.AppCache
import com.hb.app.data.cache.ICache
import com.hb.app.utils.image.GlideImageHelper
import com.hb.base.TOKEN_TAG
import com.hb.base.rx.RxBus
import com.hb.base.utils.ImageHelper
import com.hb.base.utils.NetworkVerifier
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import timber.log.Timber

val appModule = module {
  single { RxBus() }
  single<ICache> { AppCache(androidContext()) }
  single<ImageHelper> { GlideImageHelper() }
  single { androidContext().getSharedPreferences(androidContext().packageName, MODE_PRIVATE) }
  single(named("token")) { getToken(get()) }
  single { createNetworkVerifier(get()) }
}

private fun getToken(pref: SharedPreferences): String = pref.getString(TOKEN_TAG, "").orEmpty()

class MyDebugTree : Timber.DebugTree() {
  override fun createStackElementTag(element: StackTraceElement): String {
    return String.format(
      "[L:%s] [M:%s] [C:%s]",
      element.lineNumber,
      element.methodName,
      super.createStackElementTag(element)
    )
  }
}

private fun createNetworkVerifier(context: Context): NetworkVerifier {
  return object : NetworkVerifier {
    override fun isConnected(): Boolean {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
          // for other device how are able to connect with Ethernet
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
          // for check internet over Bluetooth
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
          else -> false
        }
      } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
      }
    }
  }
}
