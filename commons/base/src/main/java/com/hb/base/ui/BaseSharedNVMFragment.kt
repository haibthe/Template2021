package com.hb.base.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.Keep
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MvRxState
import org.koin.android.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

@Keep
abstract class BaseSharedNVMFragment<
  S : MvRxState,
  VMShared : BaseViewModel<S>,
  VB : ViewBinding>(
  vmShared: KClass<VMShared>
) : BaseNVMFragment<VB>() {

  protected val vmShared: VMShared by sharedViewModel(vmShared)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observerViewModel()
  }

  protected open fun observerViewModel() {
    vmShared.subscribe(this, subscriber = this::onStateChanged)
  }

  protected open fun onStateChanged(state: S) {
  }
}
