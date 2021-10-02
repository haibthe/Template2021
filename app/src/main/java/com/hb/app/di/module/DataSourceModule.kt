package com.hb.app.di.module

import org.koin.dsl.module

val dataSourceModule = module {

    // factory<AppDataSource.Local> { LocalAppDataSource(androidContext(), get(), get()) }
    // factory { AppDataSource.createService(get(), get()) }
    //
    // // Passport
    // factory<PassportDataSource.Local> { LocalPassportDataSource(androidContext(), get(), get()) }
    // factory { PassportDataSource.createService(get()) }
    //
    // // Map
    // factory { MapDataSource.createService(get(), get()) }
    //
    // //
    // factory { VDMSDataSource.createService(get(), get(named("backend"))) }
}
