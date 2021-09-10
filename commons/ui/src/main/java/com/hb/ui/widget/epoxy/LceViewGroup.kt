package com.hb.ui.widget.epoxy

import com.hb.ui.databinding.CommonUiEpoxyLceBinding
import com.hb.ui.utils.LceAnimator

class LceViewGroup(
  private val binding: CommonUiEpoxyLceBinding,
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
    binding.recyclerView.requestModelBuild()
    LceAnimator.showContent(binding.loading, binding.content, binding.error)
  }
}
