package com.hb.base.extension

import android.view.View

fun View.gone() {
  this.visibility = View.GONE
}

fun View.goneIf() {
  if (this.visibility == View.VISIBLE) this.gone()
}

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.visibleIf() {
  if (this.visibility == View.GONE) this.visible()
}

fun View.inVisible() {
  this.visibility = View.INVISIBLE
}
