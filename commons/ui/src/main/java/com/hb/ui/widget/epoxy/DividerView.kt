package com.hb.ui.widget.epoxy

import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hb.theme.ThemeUtil
import com.hb.ui.R
import com.hb.ui.databinding.CommonUiItemDividerBinding
import timber.log.Timber

@EpoxyModelClass
abstract class DividerView : EpoxyModelWithHolder<DividerView.Holder>() {

  @EpoxyAttribute
  var height: Int = 8

  @EpoxyAttribute
  var width: Int = ViewGroup.LayoutParams.MATCH_PARENT

  @ColorRes
  @EpoxyAttribute
  var backgroundColor: Int? = null

  @ColorRes
  @EpoxyAttribute
  var separatorBackgroundColor: Int? = null

  @EpoxyAttribute
  var padding: IntArray = IntArray(4) { 0 }

  @EpoxyAttribute
  var tag: Pair<Int, Any?>? = null

  override fun getDefaultLayout(): Int {
    return R.layout.common_ui_item_divider
  }

  override fun bind(holder: Holder) {
    val context = holder.binding.root.context
    val w = this.width
    val h = this.height
    holder.binding.apply {
      vSeparator.updateLayoutParams {
        width = if (w > 0) {
          ThemeUtil.dpToPx(context, w)
        } else {
          w
        }
        height = if (h > 0) {
          ThemeUtil.dpToPx(context, h)
        } else {
          h
        }
      }

      setColor(root, backgroundColor)
      setColor(vSeparator, separatorBackgroundColor)

      Timber.d(padding.joinToString(","))
      root.updatePadding(
        ThemeUtil.dpToPx(context, padding[0]),
        ThemeUtil.dpToPx(context, padding[1]),
        ThemeUtil.dpToPx(context, padding[2]),
        ThemeUtil.dpToPx(context, padding[3]),
      )
      root.setTagProp(tag)
    }
  }

  private fun setColor(view: View, @ColorRes color: Int? = null) {
    val context = view.context
    if (color == null || color == 0) {
      view.background = null
    } else {
      view.setBackgroundColor(ContextCompat.getColor(context, color))
    }
  }

  class Holder : EpoxyHolder() {

    lateinit var binding: CommonUiItemDividerBinding

    override fun bindView(itemView: View) {
      binding = CommonUiItemDividerBinding.bind(itemView)
    }
  }
}
