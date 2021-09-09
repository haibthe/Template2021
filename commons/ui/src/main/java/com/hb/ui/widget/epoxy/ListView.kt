package com.hb.ui.widget.epoxy

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.hb.theme.ThemeUtil
import com.hb.ui.R
import com.hb.ui.databinding.CommonUiListBinding

@EpoxyModelClass
abstract class ListView : EpoxyModelWithHolder<ListView.Holder>() {

  @EpoxyAttribute(value = [EpoxyAttribute.Option.DoNotHash])
  var controller: EpoxyController? = null

  @EpoxyAttribute
  var backgroundRes: Int? = 0

  @EpoxyAttribute
  var padding: IntArray = intArrayOf(16, 4, 16, 4)

  @EpoxyAttribute
  var dividerResId: Int = R.drawable.divider_transparent_small

  @EpoxyAttribute
  var orientation: Int = RecyclerView.HORIZONTAL

  private var itemDecoration: DividerItemDecoration? = null

  override fun getDefaultLayout(): Int {
    return R.layout.common_ui_list
  }

  override fun bind(holder: Holder) {
    val context = holder.binding.root.context
    if (controller == null)
      return

    if (itemDecoration == null && dividerResId != 0) {
      itemDecoration = DividerItemDecoration(context, orientation).apply {
        ContextCompat.getDrawable(context, dividerResId)?.let { setDrawable(it) }
      }
      holder.binding.recyclerView.apply {
        itemDecoration?.let { addItemDecoration(it) }
      }
    }

    holder.binding.apply {
      setBkgColor(root, backgroundRes)
      recyclerView.updatePadding(
        ThemeUtil.dpToPx(context, padding[0]),
        ThemeUtil.dpToPx(context, padding[1]),
        ThemeUtil.dpToPx(context, padding[2]),
        ThemeUtil.dpToPx(context, padding[3]),
      )

      recyclerView.apply {
        layoutManager = LinearLayoutManager(context, orientation, false)
        setController(controller!!)
        controller!!.requestModelBuild()
      }
    }
  }

  override fun unbind(holder: Holder) {

    itemDecoration?.let { holder.binding.recyclerView.removeItemDecoration(it) }
    itemDecoration = null

    super.unbind(holder)
  }

  private fun setBkgColor(view: View, resId: Int? = null) {
    if (resId == null || resId == 0) {
      view.background = null
    } else {
      view.setBackgroundResource(resId)
    }
  }

  class Holder : EpoxyHolder() {

    lateinit var binding: CommonUiListBinding

    override fun bindView(itemView: View) {
      binding = CommonUiListBinding.bind(itemView)
    }
  }
}
