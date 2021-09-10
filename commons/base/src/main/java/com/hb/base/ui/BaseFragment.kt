package com.hb.base.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRxState
import com.hb.base.rx.RxBus
import com.hb.base.utils.BaseSchedulerProvider
import com.hb.base.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

@Keep
abstract class BaseFragment<S : MvRxState, VM : BaseViewModel<S>, VB : ViewBinding>(vm: KClass<VM>) :
  BaseMvRxFragment() {

  protected val viewModel: VM by lazy { getViewModel(vm) }

  protected var _binding: VB? = null

  protected val binding get() = _binding!!

  protected val rxBus: RxBus by inject()
  protected val disposable = CompositeDisposable()

  protected val sharedPreferences: SharedPreferences by inject()
  private val schedulerProvider: BaseSchedulerProvider by inject()

  abstract fun initUIComponent()
//    abstract fun observerViewModel()

  open fun busEvent(obj: Any) {
  }

  abstract fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = onCreateBinding(inflater, container)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUIComponent()
    observerViewModel()

    disposable.clear()
    disposable.addAll(
      rxBus.toObservable()
        .compose(RxUtil.applySchedulers(schedulerProvider))
        .subscribe(::busEvent)
    )
  }

  override fun onDestroyView() {
    disposable.clear()
    super.onDestroyView()
  }

  protected open fun observerViewModel() {
    viewModel.subscribe(this, subscriber = this::onStateChanged)
  }

  protected open fun onStateChanged(state: S) {
    postInvalidate()
  }
}
