package com.zywczas.featureguestslist

import com.zywczas.featureguestslist.viewmodel.GuestsListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureGuestsListModule = module {
    viewModelOf(::GuestsListViewModel)
}
