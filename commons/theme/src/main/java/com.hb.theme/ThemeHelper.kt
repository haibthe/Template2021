package com.hb.theme

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue

object ThemeHelper {

  fun getAccentColor(context: Context): Int {
    val typedValue = TypedValue()
    val a: TypedArray =
      context.obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.colorAccent))
    val color = a.getColor(0, 0)
    a.recycle()
    return color
  }

  fun getPrimaryColor(context: Context): Int {
    val typedValue = TypedValue()
    val a: TypedArray =
      context.obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.colorPrimary))
    val color = a.getColor(0, 0)
    a.recycle()
    return color
  }

  fun getPrimaryDarkColor(context: Context): Int {
    val typedValue = TypedValue()
    val a: TypedArray =
      context.obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.colorPrimaryDark))
    val color = a.getColor(0, 0)
    a.recycle()
    return color
  }
}
