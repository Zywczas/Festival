package com.zywczas.featureguestslist.domain

import com.zywczas.commonutil.festivalmodels.Zone

data class Guest(
    val name: String = "",
    val description: String = "",
    val zones: List<Zone> = emptyList(),
)
