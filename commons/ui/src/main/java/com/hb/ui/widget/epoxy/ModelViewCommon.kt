package com.hb.ui.widget.epoxy

import android.view.View
import com.hb.ui.R

fun View.setTagProp(newTag: Pair<Int, Any?>?) {
  val previousTag = getTag(R.id.modelViewCommonTagProp) as? Pair<Int, Any?>
  if (previousTag != null) {
    setTag(previousTag.first, null)
  }
  if (newTag != null) {
    setTag(newTag.first, newTag.second)
  }
  setTag(R.id.modelViewCommonTagProp, newTag)
}
