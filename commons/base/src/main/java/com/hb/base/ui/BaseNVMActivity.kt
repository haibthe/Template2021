package com.hb.base.ui

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hb.base.rx.RxBus
import com.hb.base.utils.BaseSchedulerProvider
import com.hb.base.utils.RxUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import timber.log.Timber

@Keep
abstract class BaseNVMActivity<VB : ViewBinding> : AppCompatActivity() {

  protected val binding: VB by lazy { createBinding() }

  protected val sharedPreferences: SharedPreferences by inject()
  private val schedulerProvider: BaseSchedulerProvider by inject()

  protected val rxBus: RxBus by inject()
  private val disposable = CompositeDisposable()

  abstract fun initUIComponent()
  abstract fun createBinding(): VB

  open fun busEvent(obj: Any) {
    Timber.d("%s", obj.javaClass.name)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initUIComponent()

    setContentView(binding.root)

    disposable.clear()
    disposable.addAll(
      rxBus.toObservable()
        .compose(RxUtil.applySchedulers(schedulerProvider))
        .subscribe(::busEvent)
    )
  }

  override fun onDestroy() {
    disposable.clear()
    super.onDestroy()
  }

  protected fun enableFullScreenMode() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)

    if (Build.VERSION.SDK_INT in 19..20) {
      setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
    }
    if (Build.VERSION.SDK_INT >= 19) {
      window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
    if (Build.VERSION.SDK_INT >= 21) {
      setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
      window.statusBarColor = Color.TRANSPARENT
    }

    val attrs = window.attributes
    attrs.flags = attrs.flags xor WindowManager.LayoutParams.FLAG_FULLSCREEN
    attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
    window.attributes = attrs
  }

  private fun setWindowFlag(bits: Int, on: Boolean) {
    val win = window
    val winParams = win.attributes
    if (on) {
      winParams.flags = winParams.flags or bits
    } else {
      winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
  }
}
