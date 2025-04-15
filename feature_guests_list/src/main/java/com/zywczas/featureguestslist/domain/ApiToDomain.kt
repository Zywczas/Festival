package com.zywczas.featureguestslist.domain

import androidx.compose.ui.util.fastFilterNotNull
import com.zywczas.commonutil.Chars
import com.zywczas.commonutil.R
import com.zywczas.commonutil.StringProvider
import com.zywczas.commonutil.festivalmodels.Zone
import com.zywczas.networkplaces.response.GuestResponse

internal fun GuestResponse.toDomain(stringProvider: StringProvider): Guest {
    val domainZones = getZones(zones)
    return Guest(
        name = name,
        zones = domainZones,
        description = getDescription(domainZones, stringProvider)
    )
}

private fun getZones(zones: List<String>): List<Zone> = zones.map {
    when (it) {
        "literacka" -> Zone.Literature
        "komiksowa" -> Zone.Comic
        "filmowa" -> Zone.Movie
        "naukowa" -> Zone.Science
        "cosplay" -> Zone.Cosplay
        "muzyczna" -> Zone.Music
        else -> null
    }
}.fastFilterNotNull()

private fun getDescription(zones: List<Zone>, stringProvider: StringProvider): String =
    stringProvider.getString(R.string.zones_title) +
            Chars.COLON +
            Chars.SPACE +
            zones.joinToString { stringProvider.getString(it.displayedName) }
