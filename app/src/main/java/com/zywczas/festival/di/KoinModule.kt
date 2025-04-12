package com.zywczas.festival.di

import com.zywczas.commonutil.commonUtilModule
import com.zywczas.featureguestslist.featureGuestsListModule
import com.zywczas.networkcaller.networkCallerModule
import com.zywczas.networkplaces.networkGuestsModule
import org.koin.core.module.Module

val appKoinModules: List<Module> = listOf(
  commonUtilModule,
  featureGuestsListModule,
  networkCallerModule,
  networkGuestsModule,
)
