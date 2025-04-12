package com.zywczas.networkplaces.usecase

import android.content.Context
import com.google.gson.Gson
import com.zywczas.commonutil.Resource
import com.zywczas.commonutil.logD
import com.zywczas.networkguests.R
import com.zywczas.networkplaces.response.GuestResponse
import com.zywczas.networkplaces.retrofitapi.GuestsRetrofitApi

@Suppress("UnusedPrivateProperty") // Suppressed, because "GuestsRetrofitApi" is not used as REST API doesn't work. Otherwise it would be used.
class GetGuestsUseCase internal constructor(private val api: GuestsRetrofitApi) {

    suspend fun get(context: Context): Resource<List<GuestResponse>> = try {
        // As REST API is not working, we need to read mocked data from file.
        // Resource.Success(api.getGuests().body()!!)
        Resource.Success(loadGuestsFromFile(context))
    } catch (e: Exception) {
        logD(e.message)
        Resource.Error(R.string.error_guests_download)
    }
}

private fun loadGuestsFromFile(context: Context): List<GuestResponse> {
    val inputStream = context.assets.open("guests.json")
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    val json = String(buffer, charset = Charsets.UTF_8)
    val guests = Gson().fromJson(json, Array<GuestResponse>::class.java)
    return guests.toList()
}
