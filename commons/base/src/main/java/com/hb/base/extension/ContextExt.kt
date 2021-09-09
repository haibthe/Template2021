package com.hb.base.extension

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

fun Context.getDrawableCompat(@DrawableRes idRes: Int): Drawable? {
  return ContextCompat.getDrawable(this, idRes)
}

fun Context.getColorCompat(colorInt: Int?): Int? {
  return colorInt?.let { ContextCompat.getColor(this, it) }
}

/**
 * InflateLayout
 */
fun Context.inflateLayout(
  @LayoutRes layoutId: Int,
  parent: ViewGroup? = null,
  attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

fun Context.getResId(type: String, name: String): Int {
  return resources.getIdentifier(name, type, packageName)
}

fun Context.checkPermission(permission: String): Boolean {
  return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}
