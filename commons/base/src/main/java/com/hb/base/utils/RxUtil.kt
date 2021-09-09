package com.hb.base.utils

import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

object RxUtil {

  fun <T> applySchedulers(schedulerProvider: BaseSchedulerProvider): ObservableTransformer<T, T> {
    return ObservableTransformer<T, T> { observable ->
      observable.subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
    }
  }

  fun <T> applySingleSchedulers(schedulerProvider: BaseSchedulerProvider): SingleTransformer<T, T> {
    return SingleTransformer<T, T> { observable ->
      observable.subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
    }
  }
}
