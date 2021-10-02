package com.hb.app.domain.usecases

import com.hb.base.utils.BaseSchedulerProvider
import com.hb.base.utils.RxUtil
import io.reactivex.Observable

interface AppUseCase {

    fun loadData(): Observable<Boolean>
}

class AppUseCaseImpl(
    private val schedulerProvider: BaseSchedulerProvider,
) : AppUseCase {

    override fun loadData(): Observable<Boolean> {
        return Observable.just(true)
            .compose(RxUtil.applySchedulers(schedulerProvider))
    }
}
