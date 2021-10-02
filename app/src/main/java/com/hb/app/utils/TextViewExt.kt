package com.hb.f88.app.utils

import android.widget.TextView

fun TextView.setChecked(isChecked: Boolean) {
    isSelected = isChecked
    isPressed = isChecked
}
