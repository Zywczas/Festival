package com.zywczas.featureguestslist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.zywczas.commonutil.BaseViewModel
import com.zywczas.commonutil.Resource
import com.zywczas.commonutil.StringProvider
import com.zywczas.featureguestslist.domain.Guest
import com.zywczas.featureguestslist.domain.toDomain
import com.zywczas.networkplaces.usecase.GetGuestsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class GuestsListViewModel(
    private val stringProvider: StringProvider,
    private val getGuestsUseCase: GetGuestsUseCase
) : BaseViewModel() {

    var guests by mutableStateOf<List<Guest>>(emptyList())
        private set

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            downloadGuestsList()
        }
    }

    private suspend fun downloadGuestsList() {
        when (val result = getGuestsUseCase.get()) {
            is Resource.Success -> guests = result.data.map { it.toDomain(stringProvider) }
            is Resource.Error -> showError(stringProvider.getString(result.message))
        }
    }
}
