package com.zywczas.networkplaces.response

import com.google.gson.annotations.SerializedName

data class GuestResponse(

  @SerializedName("name")
  val name: String = "",

  @SerializedName("summary")
  val summary: String = "",

  @SerializedName("imageURL")
  val imageURL: String = "",

  @SerializedName("zones")
  val zones: List<String> = emptyList(),
)
