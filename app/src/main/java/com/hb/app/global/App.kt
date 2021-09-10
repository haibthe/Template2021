package com.hb.app.global

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.hb.app.BuildConfig
import com.hb.base.utils.TrimSign
import com.hb.app.di.module.MyDebugTree
import com.hb.app.di.module.appModule
import com.hb.app.di.module.dataSourceModule
import com.hb.app.di.module.networkModule
import com.hb.app.di.module.repositoryModule
import com.hb.app.di.module.useCaseModule
import com.hb.app.di.module.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber
import java.io.File

class App : MultiDexApplication() {

  lateinit var folder: String

  override fun onCreate() {
    super.onCreate()


    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    startKoin {
      androidLogger()
      androidContext(applicationContext)

      modules(allModules)
    }
    TrimSign.getInstances()
    setupDebugTool()
    initAppFolder(this)
  }

  private val allModules = arrayListOf<Module>(
    appModule,
    networkModule,
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    vmModule
  )

  private fun setupDebugTool() {
    initTimber()
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(MyDebugTree())
    }
  }

  private fun initAppFolder(context: Context) {
    val fileTemp: File? = context.externalCacheDir
    folder = if (fileTemp != null && fileTemp.exists()) {
      fileTemp.absolutePath
    } else {
      context.cacheDir.absolutePath
    }
    folder += File.separator
  }
}