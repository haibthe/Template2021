package com.hb.base.extension

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private val LOCALE = Locale.US

fun String.toDateFormat(
  parserStr: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
  pattern: String = "dd.MM.yyyy HH:mm"
): String {
  return try {
    val parser = SimpleDateFormat(parserStr, LOCALE)
    val formatter = SimpleDateFormat(pattern, LOCALE)
    formatter.format(
      parser.parse(this)
    )
  } catch (e: Exception) {
    Timber.e(e)
    this
  }
}

fun String.toDate(
  parserStr: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
): Date {
  val parser = SimpleDateFormat(parserStr, LOCALE)
  return parser.parse(this) ?: Date()
}

fun String.toDateForSort(
  parserStr: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
): Int {
  val parser = SimpleDateFormat(parserStr, LOCALE)
  val formatter = SimpleDateFormat("yyyyMMdd", LOCALE)
  val d = parser.parse(this) ?: Date()
  return formatter.format(d).toInt()
}

fun String.toDateTitle(
  parserStr: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
  format: String = "EEE, dd MMMM yyyy"
): String {
  val date = toDate(parserStr)
  val smsTime = Calendar.getInstance(LOCALE)
  smsTime.time = date
  val now = Calendar.getInstance(LOCALE)

  val sdf = SimpleDateFormat(format, LOCALE)

  if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
    return "Today"
  } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
    return "Yesterday"
  }
  return sdf.format(date).toString()
}

fun Date.toStringFormat(pattern: String = "dd.MM.yyyy HH:mm"): String {
  val formatter = SimpleDateFormat(pattern, LOCALE)
  return formatter.format(this)
}
