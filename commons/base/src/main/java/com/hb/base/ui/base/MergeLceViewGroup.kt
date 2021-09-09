package com.hb.base.ui.base

import android.view.View
import com.hb.base.databinding.MergeLoadingContentErrorBinding
import com.hb.base.utils.LceAnimator

class MergeLceViewGroup(
  rootView: View,
  onRefresh: () -> Unit,
) {
  private val binding: MergeLoadingContentErrorBinding =
    MergeLoadingContentErrorBinding.bind(rootView)

  init {
    binding.content.setOnRefreshListener {
      onRefresh()
    }
  }

  fun getBinding(): MergeLoadingContentErrorBinding = binding

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
    LceAnimator.showContent(binding.loading, binding.content, binding.error)
  }
}
