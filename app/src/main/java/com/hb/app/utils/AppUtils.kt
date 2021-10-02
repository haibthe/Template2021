package com.hb.app.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

object AppUtils {
    fun copyToClipboard(context: Context, title: CharSequence, value: CharSequence) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(title, value)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copy: $title - $value", Toast.LENGTH_LONG).show()
    }
}
