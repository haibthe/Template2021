package com.hb.base.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

// {"error":"invalid_grant","error_description":"The user name or password is incorrect."}
@Keep
data class ErrorResponse(
  @SerializedName("error")
  val error: String?,
  @SerializedName("error_description")
  val errorDescription: String?
)
