package com.zywczas.festival.di

import com.zywczas.commonutil.commonUtilModule
import com.zywczas.networkcaller.networkCallerModule
import com.zywczas.networkplaces.guestsModule
import org.koin.core.module.Module

val appKoinModules: List<Module> = listOf(
  commonUtilModule,
  guestsModule,
  networkCallerModule,
)
