package com.hb.base.data.model

import com.google.gson.annotations.SerializedName

data class ObjectFile(
  @SerializedName("guid")
  val guid: String? = null,
  @SerializedName("mimetype")
  val mimetype: String? = null,
  @SerializedName("name")
  val name: String? = null
) {
  fun isImage(): Boolean {
    return (
      mimetype?.contains("image") == true ||
        mimetype?.contains("video") == true
      )
  }

  fun getImageUrl(url: String, w: Int? = null, h: Int? = null): String {
    return ""
  }
}
