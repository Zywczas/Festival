package com.zywczas.featureguestslist.viewmodel

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.zywczas.commonutil.BaseViewModel
import com.zywczas.commonutil.Chars
import com.zywczas.commonutil.Constants
import com.zywczas.commonutil.Resource
import com.zywczas.commonutil.StringProvider
import com.zywczas.commonutil.logD
import com.zywczas.featureguestslist.domain.Guest
import com.zywczas.featureguestslist.domain.toDomain
import com.zywczas.networkplaces.usecase.GetGuestsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class GuestsListViewModel(
    private val stringProvider: StringProvider,
    private val getGuestsUseCase: GetGuestsUseCase
) : BaseViewModel() {

    private var downloadedGuestsList by mutableStateOf<List<Guest>>(emptyList())
    private var searchQuery by mutableStateOf(Chars.EMPTY_STRING)
    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val guests by derivedStateOf<List<Guest>> {
        downloadedGuestsList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    var searchText by mutableStateOf(TextFieldValue())
        private set

    fun init(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            downloadGuestsList(context)
        }
        subscribeToSearchQuery()
    }

    fun onSearchTextChanged(textFieldValue: TextFieldValue) {
        searchText = textFieldValue
        searchQueryFlow.tryEmit(textFieldValue.text)
    }

    private suspend fun downloadGuestsList(context: Context) {
        when (val result = getGuestsUseCase.get(context)) {
            is Resource.Success -> downloadedGuestsList = result.data.map { it.toDomain(stringProvider) }
            is Resource.Error -> showError(stringProvider.getString(result.message))
        }
    }

    @OptIn(FlowPreview::class)
    private fun subscribeToSearchQuery() {
        searchQueryFlow
            .debounce(Constants.DEBOUNCE)
            .distinctUntilChanged()
            .onEach { searchQuery = it }
            .catch { cause -> logD(cause) }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}
