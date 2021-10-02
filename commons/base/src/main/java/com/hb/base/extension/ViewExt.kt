package com.hb.base.extension

import android.view.View
import com.hb.base.R

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

fun View.setOnClickListenerThrottle(throttleMs: Long = 300, callback: ((View) -> Unit)?) {
  setOnClickListenerThrottle(
    throttleMs,
    callback?.let { View.OnClickListener { v -> callback(v) } }
  )
}

fun View.setOnClickListenerThrottle(throttleMs: Long = 300, callback: View.OnClickListener?) {
  setOnClickListener(
    callback?.let {
      View.OnClickListener {
        val lastClickTime = (getTag(R.id.last_click_time) as? Long) ?: 0
        if (System.currentTimeMillis() - lastClickTime > throttleMs) {
          setTag(R.id.last_click_time, System.currentTimeMillis())
          callback.onClick(this)
        }
      }
    }
  )
}
