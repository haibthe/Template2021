package com.hb.app.di.module

import org.koin.dsl.module

val repositoryModule = module {

    // factory<PassportRepository> { PassportRepositoryImpl(get(), get()) }
    // factory<AppRepository> { AppRepositoryImpl(get(), get(), get(), get()) }
    // factory<MapRepository> { MapRepositoryImpl(get()) }
}
