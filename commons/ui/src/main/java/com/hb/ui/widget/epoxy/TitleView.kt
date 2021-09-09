package com.hb.ui.widget.epoxy

import android.view.View
import androidx.core.view.updatePadding
import androidx.core.widget.TextViewCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hb.theme.ThemeUtil
import com.hb.ui.R
import com.hb.ui.databinding.CommonUiTitleViewBinding

@EpoxyModelClass
abstract class TitleView : EpoxyModelWithHolder<TitleView.Holder>() {

  @EpoxyAttribute
  var title: String = ""

  @EpoxyAttribute
  var textAppearanceId: Int? = 0

  @EpoxyAttribute
  var padding: IntArray = IntArray(4) {0}

  override fun getDefaultLayout(): Int {
    return R.layout.common_ui_title_view
  }

  override fun bind(holder: Holder) {
    holder.binding.apply {
      val context = root.context

      tvTitle.text = title
      if (textAppearanceId != null && textAppearanceId != 0) {
        TextViewCompat.setTextAppearance(holder.binding.tvTitle, textAppearanceId!!)
      }
      root.updatePadding(
        ThemeUtil.dpToPx(context, padding[0]),
        ThemeUtil.dpToPx(context, padding[1]),
        ThemeUtil.dpToPx(context, padding[2]),
        ThemeUtil.dpToPx(context, padding[3]),
      )
    }
  }

  class Holder : EpoxyHolder() {

    lateinit var binding: CommonUiTitleViewBinding

    override
    fun bindView(itemView: View) {
      binding = CommonUiTitleViewBinding.bind(itemView)
    }
  }
}
