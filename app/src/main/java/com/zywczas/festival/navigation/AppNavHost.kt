package com.zywczas.festival.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zywczas.featureguestslist.ui.GuestsListScreen

@Composable
fun AppNavHost() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = Destination.GuestsList) {
    composable<Destination.GuestsList> {
      GuestsListScreen()
    }
  }
}
