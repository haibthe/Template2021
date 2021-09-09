package com.hb.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hb.base.rx.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import timber.log.Timber

@Keep
abstract class BaseNVMFragment<VB : ViewBinding> : Fragment() {

  protected var _binding: VB? = null

  protected val binding get() = _binding!!

  protected val rxBus: RxBus by inject()
  protected val disposable = CompositeDisposable()

  abstract fun initUIComponent()

  open fun busEvent(obj: Any) {
    Timber.d("%s", obj.javaClass.name)
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

    disposable.clear()
    disposable.addAll(
      rxBus.toObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(::busEvent)
    )
  }

  override fun onDestroyView() {
    disposable.clear()
    super.onDestroyView()
  }
}
