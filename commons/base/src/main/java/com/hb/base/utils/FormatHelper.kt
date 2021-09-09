package com.hb.base.utils

import android.text.SpannableString
import android.text.TextUtils
import android.text.style.StrikethroughSpan
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object FormatHelper {
  fun formatPriceText(price: Long): String {
    return internalFormatPriceText(price, true)
  }

  fun formatPriceTextWithoutSpacing(price: Long): String {
    return internalFormatPriceText(price, false)
  }

  fun formatPriceText(price: Double): String {
    return internalFormatPriceText(price, true)
  }

  fun formatPriceTextWithoutSpacing(price: Double): String {
    return internalFormatPriceText(price, false)
  }

  private fun internalFormatPriceText(price: Long, withSpacing: Boolean): String {
    val formatter = DecimalFormat("#,###,###,###")
    val unit = if (withSpacing) " K" else "K"
    var result = formatter.format(price) + unit
    result = result.replace(",", ".")
    return result
  }

  private fun internalFormatPriceText(price: Double, withSpacing: Boolean): String {
    val formatter = DecimalFormat("#,###,###,###")
    val unit = if (withSpacing) " K" else "K"
    var result = formatter.format(price) + unit
    result = result.replace(",", ".")
    return result
  }

  fun formatOriginalPriceText(price: Double): CharSequence {
    val result = formatPriceText(price)
    val priceSpan = SpannableString(result)
    priceSpan.setSpan(StrikethroughSpan(), 0, priceSpan.length, 0)
    return priceSpan
  }

  fun formatDate(dateStr: String?): String {
    if (!TextUtils.isEmpty(dateStr)) {
      var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
      try {
        val date = dateFormat.parse(dateStr)
        dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        return dateFormat.format(date)
      } catch (e: ParseException) {
        e.printStackTrace()
      }
    }
    return ""
  }

  fun <T> formatPoint(point: T): String {
    var result = point.toString()
    try {
      val formatter = DecimalFormat("#,###,###,###")
      result = formatter.format(point)
      result = result.replace(",", ".")
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return result
  }

  fun formatTime(time: Long): String {
    val des = Calendar.getInstance(Locale.getDefault())
    des.timeInMillis = time * 1000
    val dateFormat = SimpleDateFormat("HH:mm, dd/MM/yyyy", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("GMT+7")
    return dateFormat.format(des.time)
  }

  fun formatTime(time: Long, pattern: String?): String {
    val des = Calendar.getInstance(Locale.getDefault())
    des.timeInMillis = time * 1000
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("GMT+7")
    return dateFormat.format(des.time)
  }
}
