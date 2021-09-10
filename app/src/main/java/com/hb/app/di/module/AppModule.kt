package com.hb.app.di.module

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.hb.app.data.cache.AppCache
import com.hb.app.data.cache.ICache
import com.hb.app.utils.image.GlideImageHelper
import com.hb.base.TOKEN_TAG
import com.hb.base.rx.RxBus
import com.hb.base.utils.ImageHelper
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
