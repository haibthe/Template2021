package com.hb.base.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>
@JvmOverloads
constructor(
  @SerializedName("data")
  val data: T? = null,
  @SerializedName("status")
  val status: Status? = null,
  @SerializedName("result")
  val result: Int = 0,
  @SerializedName("errorMessage")
  val errorMessage: String? = null,
) {

  data class Status(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("type")
    val type: String?
  )
}
