package com.hb.app.di.module

import com.hb.app.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
  viewModel { MainViewModel() }
}
