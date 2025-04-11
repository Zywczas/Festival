package com.zywczas.networkplaces.usecase

import com.zywczas.commonutil.Resource
import com.zywczas.commonutil.logD
import com.zywczas.networkguests.R
import com.zywczas.networkplaces.response.GuestsResponse
import com.zywczas.networkplaces.retrofitapi.GuestsRetrofitApi

@Suppress("UnusedPrivateProperty") // Suppressed, because "GuestsRetrofitApi" is not used as REST API doesn't work. Otherwise it would be used.
class GetGuestsUseCase internal constructor(private val api: GuestsRetrofitApi) {

  suspend fun get(): Resource<List<GuestsResponse>> = try {
    // As REST API is not working, we need to read mocked data from file.
    // Resource.Success(api.getGuests().body()!!)
    Resource.Success(emptyList())
  } catch (e: Exception) {
    logD(e.message)
    Resource.Error(R.string.error_guests_download)
  }
}
