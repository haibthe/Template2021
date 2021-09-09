package com.hb.base.extension

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.MimeTypeMap

fun Context.getMimeType(uri: Uri): String {
  var type = "image/jpeg" // Default Value
  val extension = MimeTypeMap.getFileExtensionFromUrl(this.contentResolver.getType(uri))
  if (extension != null) {
    type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) ?: "image/jpeg"
  }
  return type
}

fun getImageExtension(path: String?): String {
  var extension = MimeTypeMap.getFileExtensionFromUrl(path)
  if (extension.isNullOrEmpty()) extension = path?.split(".")?.last()
  return extension ?: "jpg"
}

fun getCompressFormat(path: String?): Bitmap.CompressFormat {
  return if (getImageExtension(path).equals("png", true)) Bitmap.CompressFormat.PNG
  else Bitmap.CompressFormat.JPEG
}
