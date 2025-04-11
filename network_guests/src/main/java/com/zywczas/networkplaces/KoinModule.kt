package com.zywczas.networkplaces

import com.zywczas.networkcaller.createRetrofitService
import com.zywczas.networkplaces.retrofitapi.GuestsRetrofitApi
import com.zywczas.networkplaces.usecase.GetGuestsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkGuestsModule = module {
    single { createRetrofitService<GuestsRetrofitApi>() }
    singleOf(::GetGuestsUseCase)
}
