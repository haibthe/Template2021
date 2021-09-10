package com.hb.app.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hb.app.databinding.MainFragmentBinding
import com.hb.base.ui.BaseFragment

class MainFragment : BaseFragment<MainState, MainViewModel, MainFragmentBinding>(MainViewModel::class) {

  override fun onCreateBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
  ): MainFragmentBinding {
    return MainFragmentBinding.inflate(inflater, container, false)
  }

  override fun initUIComponent() {
    viewModel.loadData()
  }

  override fun observerViewModel() {
  }

  override fun invalidate() {

  }
}