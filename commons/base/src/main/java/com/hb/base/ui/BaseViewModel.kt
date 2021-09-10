package com.hb.base.ui

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.hb.base.BuildConfig
import org.koin.core.KoinComponent

abstract class BaseViewModel<S : MvRxState>(
  initialState: S
) : BaseMvRxViewModel<S>(initialState, BuildConfig.DEBUG), KoinComponent
