package com.hb.app.ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized

data class MainState(
  val test: Boolean = false,
  val loadDataRequest: Async<Boolean> = Uninitialized,
) : MvRxState