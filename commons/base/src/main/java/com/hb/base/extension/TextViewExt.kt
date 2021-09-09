package com.hb.base.extension

import android.widget.TextView

fun TextView.setChecked(isChecked: Boolean) {
  isSelected = isChecked
  isPressed = isChecked
}
