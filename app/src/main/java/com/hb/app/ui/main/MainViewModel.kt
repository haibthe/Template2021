package com.hb.app.ui.main

import com.airbnb.mvrx.Success
import com.hb.app.domain.usecases.AppUseCase
import com.hb.base.ui.BaseViewModel
import org.koin.core.inject

class MainViewModel : BaseViewModel<MainState>(MainState()) {

    private val appUseCase: AppUseCase by inject()

    fun loadData() {
        appUseCase.loadData().execute { request ->
            with(copy(loadDataRequest = request)) {
                when (request) {
                    is Success -> {
                        copy()
                    }
                    else -> copy()
                }
            }
        }
    }
}
