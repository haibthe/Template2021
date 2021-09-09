package com.hb.base.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.annotation.Keep
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MvRxState
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

@Keep
abstract class BaseSharedVMFragment<
  S : MvRxState,
  VM : BaseViewModel<S>,
  VMShared : BaseViewModel<S>,
  VB : ViewBinding>(
  vm: KClass<VM>,
  vmShared: KClass<VMShared>
) : BaseFragment<S, VM, VB>(vm) {

  protected val vmShared: VMShared by sharedViewModel(vmShared)

  val sharedPreferences: SharedPreferences by inject()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observerViewModel()
  }

  override fun observerViewModel() {
    super.observerViewModel()
    vmShared.subscribe(this, subscriber = this::onStateChanged)
  }
}
