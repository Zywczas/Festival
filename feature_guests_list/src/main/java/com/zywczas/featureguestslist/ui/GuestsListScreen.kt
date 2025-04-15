package com.zywczas.featureguestslist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.commoncompose.components.BeansGroup
import com.zywczas.commoncompose.components.BeansViewEntity
import com.zywczas.commoncompose.components.BottomBarInsetSpacer
import com.zywczas.commoncompose.components.GuestListItem
import com.zywczas.commoncompose.components.HorizontalListItemDivider
import com.zywczas.commoncompose.components.OutlinedTextInput
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
        guests = viewModel.displayedGuests,
        searchText = viewModel.searchText,
        onSearchTextChanged = viewModel::onSearchTextChanged,
        onZoneFilterClick = viewModel::onZoneFilterClick,
        beansViewEntity = viewModel.beansViewEntity
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
    guests: List<Guest>,
    searchText: TextFieldValue,
    onSearchTextChanged: (TextFieldValue) -> Unit,
    beansViewEntity: List<BeansViewEntity>,
    onZoneFilterClick: (index: Int) -> Unit,
) {
    Column {
        Toolbar(stringResource(R.string.guests_list_title))

        Row(Modifier.padding(horizontal = Spacing.screenBorder)) {
            OutlinedTextInput(
                hint = stringResource(R.string.search_guest),
                value = searchText,
                onValueChange = onSearchTextChanged
            )
        }

        Spacer(Modifier.height(Spacing.screenComponentsVertical))

        BeansGroup(
            beans = beansViewEntity,
            onClick = onZoneFilterClick
        )

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
                    description = "Zones: cosplay, movie",
                ),
                Guest(
                    name = "Vin Diesel",
                    description = "Zones: cosplay, movie",
                ),
                Guest(
                    name = "Vin Diesel",
                    description = "Zones: cosplay, movie",
                ),
            ),
            searchText = TextFieldValue("Search guest"),
            onSearchTextChanged = {},
            onZoneFilterClick = {},
            beansViewEntity = listOf(
                BeansViewEntity("Bean", isChecked = false),
                BeansViewEntity("Bean", isChecked = true),
                BeansViewEntity("Bean", isChecked = false),
                BeansViewEntity("Bean", isChecked = true),
            ),
        )
    }
}
