package com.hb.base.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.hb.base.data.model.ErrorResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import timber.log.Timber

abstract class BaseUseCase<T>(
  private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
  protected val liveData: MutableLiveData<T> = MutableLiveData()
) : UseCase<T> {

  protected fun Disposable.track() {
    compositeDisposable.add(this)
  }

  override fun getLiveData(): LiveData<T> = liveData

  override fun cleanUp() = compositeDisposable.clear()

  protected open fun error(throwable: Throwable) {
    Timber.e("$this $throwable")
  }

  protected open fun getErrorMessage(throwable: Throwable): String {
    return when (throwable) {
      is HttpException -> {
        try {
          val msg = throwable.response()?.errorBody()?.string().orEmpty()
          val obj: ErrorResponse = Gson().fromJson(msg, ErrorResponse::class.java)
          obj.errorDescription.orEmpty()
        } catch (e: Exception) {
          return e.message.toString()
        }
      }
      else -> throwable.message.toString()
    }
  }
}
