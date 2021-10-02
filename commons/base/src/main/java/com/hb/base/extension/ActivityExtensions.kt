package com.hb.base.extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.viewbinding.ViewBinding

fun Activity.getContentView(): ViewGroup = this.findViewById(android.R.id.content)

inline fun <T : ViewBinding> Activity.viewBinding(
  crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
  bindingInflater.invoke(layoutInflater)
}

fun Activity.context(): Context {
  return this
}

fun Activity.enableFullScreenMode() {
  requestWindowFeature(Window.FEATURE_NO_TITLE)
  val sdkInt = Build.VERSION.SDK_INT
  if (sdkInt in 19..20) {
    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
  }
  if (sdkInt >= 19) {
    window.decorView.systemUiVisibility =
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
  }
  if (sdkInt >= 21) {
    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
    window.statusBarColor = 0x33000000
  }

  val attrs = window.attributes
  attrs.flags = attrs.flags xor WindowManager.LayoutParams.FLAG_FULLSCREEN
  attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
  window.attributes = attrs
}

fun Activity.setWindowFlag(bits: Int, on: Boolean) {
  val win = window
  val winParams = win.attributes
  if (on) {
    winParams.flags = winParams.flags or bits
  } else {
    winParams.flags = winParams.flags and bits.inv()
  }
  win.attributes = winParams
}
