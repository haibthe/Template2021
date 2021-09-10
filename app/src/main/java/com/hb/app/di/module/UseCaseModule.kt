package com.hb.app.di.module

import com.hb.app.domain.usecases.AppUseCase
import com.hb.app.domain.usecases.AppUseCaseImpl
import com.hb.base.utils.BaseSchedulerProvider
import com.hb.base.utils.SchedulerProvider
import org.koin.dsl.module

val useCaseModule = module {
  single<BaseSchedulerProvider> { SchedulerProvider() }

  factory<AppUseCase> { AppUseCaseImpl(get()) }
  // factory<PassportUseCase> { PassportUseCaseImpl(get(), get(), get()) }
  // factory<MapUseCase> { MapUseCaseImpl(get(), get(), get()) }
  // factory<SystemUseCase> { SystemUseCaseImpl(get(), get(), get(), get()) }
}
