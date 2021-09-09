package com.hb.theme

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object ScreenUtils {

  @JvmStatic
  fun getWidth(context: Context): Int {
    val windowManager = context
      .getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
  }

  @JvmStatic
  fun getHeight(context: Context?): Int {
    if (context == null) return 0
    val windowManager = context
      .getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
  }

  @JvmStatic
  fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources
      .getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
      result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
  }

  @JvmStatic
  fun getToolbarHeight(context: Context): Int {
    val styledAttributes = context.theme.obtainStyledAttributes(
      intArrayOf(android.R.attr.actionBarSize)
    )
    val toolbarHeight = styledAttributes.getDimension(0, 0f).toInt()
    styledAttributes.recycle()

    return toolbarHeight
  }
}
