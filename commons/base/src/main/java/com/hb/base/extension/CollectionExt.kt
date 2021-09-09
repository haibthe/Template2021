package com.hb.base.extension

fun <E> List<E>.clearAndAddAll(itemList: List<E>?): List<E> {
  val tmp = this.toMutableList()
  with(tmp) {
    clear()
    itemList?.let { tmp.addAll(it) }
  }
  return if (tmp.isNullOrEmpty()) this else tmp
}
