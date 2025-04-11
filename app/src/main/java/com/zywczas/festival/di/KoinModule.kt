package com.zywczas.festival.di

import com.zywczas.networkcaller.networkCallerModule
import org.koin.core.module.Module

val appKoinModules: List<Module> = listOf(
  networkCallerModule
)
