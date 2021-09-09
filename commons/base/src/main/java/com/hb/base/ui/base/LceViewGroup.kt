package com.hb.base.ui.base

import com.hb.base.databinding.ViewLoadingContentErrorBinding
import com.hb.base.utils.LceAnimator

class LceViewGroup(
  private val binding: ViewLoadingContentErrorBinding,
  onRefresh: () -> Unit,
) {
  init {
    binding.content.setOnRefreshListener {
      onRefresh()
    }
  }

  fun showLoading(pullToRefresh: Boolean = false) {
    if (!pullToRefresh) {
      LceAnimator.showLoading(binding.loading, binding.content, binding.error)
    }
  }

  fun showError(msg: String = "Lỗi hệ thống") {
    binding.textMessageError.text = msg
    LceAnimator.showErrorView(binding.loading, binding.content, binding.error)
  }

  fun showContent() {
    binding.content.isRefreshing = false
    LceAnimator.showContent(binding.loading, binding.content, binding.error)
  }
}
