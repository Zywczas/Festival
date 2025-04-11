package com.zywczas.featureguestslist.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zywczas.commoncompose.components.Toolbar
import com.zywczas.featureguestslist.R

@Composable
fun GuestsListScreen() {
  Toolbar(stringResource(R.string.guests_list_title))
}
