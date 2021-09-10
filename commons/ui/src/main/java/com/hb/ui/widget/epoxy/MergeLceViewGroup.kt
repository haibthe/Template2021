package com.hb.ui.widget.epoxy

import android.view.View
import com.hb.ui.databinding.CommonUiEpoxyMergeLceBinding
import com.hb.ui.utils.LceAnimator

class MergeLceViewGroup(
  rootView: View,
  onRefresh: () -> Unit,
) {
  private val binding: CommonUiEpoxyMergeLceBinding = CommonUiEpoxyMergeLceBinding.bind(rootView)

  init {
    binding.content.setOnRefreshListener {
      onRefresh()
    }
  }

  fun getBinding(): CommonUiEpoxyMergeLceBinding = binding

  fun showLoading(pullToRefresh: Boolean = false) {
    if (!pullToRefresh) {
      LceAnimator.showLoading(binding.loading, binding.content, binding.error)
    }
  }

  fun showError(msg: String = "Error") {
    binding.textMessageError.text = msg
    LceAnimator.showErrorView(binding.loading, binding.content, binding.error)
  }

  fun showContent() {
    binding.content.isRefreshing = false
    binding.recyclerView.requestModelBuild()
    LceAnimator.showContent(binding.loading, binding.content, binding.error)
  }
}
