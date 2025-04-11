package com.zywczas.networkplaces.retrofitapi

import com.zywczas.networkcaller.pyrkonapi.PyrkonApiUrl
import com.zywczas.networkplaces.response.GuestsResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface GuestsRetrofitApi {

  @GET(PyrkonApiUrl.GUESTS)
  suspend fun getGuests(): Response<List<GuestsResponse>>
}
