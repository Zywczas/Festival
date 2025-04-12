package com.zywczas.featureguestslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.commoncompose.components.BottomBarInsetSpacer
import com.zywczas.commoncompose.components.GuestListItem
import com.zywczas.commoncompose.components.HorizontalListItemDivider
import com.zywczas.commoncompose.components.Snackbar
import com.zywczas.commoncompose.components.Toolbar
import com.zywczas.commoncompose.theme.Spacing
import com.zywczas.commoncompose.theme.Theme
import com.zywczas.featureguestslist.R
import com.zywczas.featureguestslist.domain.Guest
import com.zywczas.featureguestslist.viewmodel.GuestsListViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GuestsListScreen() {
    val viewModel: GuestsListViewModel = koinViewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current.applicationContext
    LaunchedEffect(Unit) { viewModel.init(context) }

    GuestsListScreen(
        guests = viewModel.guests
    )

    Snackbar(snackbarHostState)

    LaunchedEffect(Unit) {
        viewModel.announcement.collectLatest { text ->
            snackbarHostState.showSnackbar(text)
        }
    }
}

@Composable
private fun GuestsListScreen(
    guests: List<Guest>
) {
    Column {
        Toolbar(stringResource(R.string.guests_list_title))

        Spacer(Modifier.height(Spacing.screenComponentsVertical))

        LazyColumn {
            itemsIndexed(guests) { index, guest ->
                GuestListItem(
                    title = guest.name,
                    subtitle = guest.description,
                    onClick = {
                        // todo go to details screen
                    }
                )

                if (index < guests.lastIndex) {
                    HorizontalListItemDivider()
                }
            }

            item {
                BottomBarInsetSpacer()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGuestsListScreen() {
    Theme.Preview {
        GuestsListScreen(
            guests = listOf(
                Guest(
                    name = "Vin Diesel",
                    description = "Zones: cosplay, movie"
                ),
                Guest(
                    name = "Vin Diesel",
                    description = "Zones: cosplay, movie"
                ),
                Guest(
                    name = "Vin Diesel",
                    description = "Zones: cosplay, movie"
                ),
            )
        )
    }
}
