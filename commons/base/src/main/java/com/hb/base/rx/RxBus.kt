package com.hb.base.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {
  private val bus: PublishSubject<Any> = PublishSubject.create()

  fun send(o: Any) {
    bus.onNext(o)
  }

  fun toObservable(): Observable<Any> {
    return bus
  }

  fun hasObservers(): Boolean {
    return bus.hasObservers()
  }
}
