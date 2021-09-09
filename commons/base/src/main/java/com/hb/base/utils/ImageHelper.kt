package com.hb.base.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView

interface ImageHelper {

  fun loadImage(view: ImageView, imageData: Any)

  fun getBitmap(context: Context, imageData: Any): Bitmap

  interface OnLoadSuccessListener {
    fun onSuccess(d: Drawable?)
  }

  interface OnLoadFailedListener {
    fun onFailed(e: Exception?)
  }
}
