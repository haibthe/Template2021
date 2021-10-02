package com.hb.base.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MvRxState
import com.hb.base.rx.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.reflect.KClass

@Keep
abstract class BaseActivity<
  S : MvRxState,
  out VM : BaseViewModel<S>,
  VB : ViewBinding>(clazz: KClass<VM>) : AppCompatActivity() {

  val viewModel: VM by lazy { getViewModel(clazz) }

  val binding: VB by lazy { createBinding() }

  val sharedPreferences: SharedPreferences by inject()

  protected val rxBus: RxBus by inject()
  private val disposable = CompositeDisposable()

  abstract fun initUIComponent()
  abstract fun createBinding(): VB

  open fun busEvent(obj: Any) {
    Timber.d("%s", obj.javaClass.name)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    initUIComponent()
    observerViewModel()

    disposable.clear()
    disposable.addAll(
      rxBus.toObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(::busEvent)
    )
  }

  protected open fun observerViewModel() {
    viewModel.subscribe(this, subscriber = this::onStateChanged)
  }

  protected open fun onStateChanged(state: S) {
  }

  override fun onDestroy() {
    disposable.clear()
    super.onDestroy()
  }
}
