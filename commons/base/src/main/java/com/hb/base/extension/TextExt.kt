package com.hb.base.extension

fun String.removePrefixAndUnderscore(): String {
  return this.removePrefix("text_").removePrefix("id_").replace("_", " ")
}

fun String.removeBracketAndSpecialChar(): String {
  return this.replace("[", "")
    .replace("\n\r", "")
    .replace("\"", "")
    .replace("]", "")
    .trim()
}
