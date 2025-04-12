package com.zywczas.featureguestslist.domain

import androidx.compose.ui.util.fastFilterNotNull
import com.zywczas.commonutil.Chars
import com.zywczas.commonutil.R
import com.zywczas.commonutil.StringProvider
import com.zywczas.networkplaces.response.GuestResponse

internal fun GuestResponse.toDomain(stringProvider: StringProvider) = Guest(
    name = name,
    description = "${stringProvider.getString(R.string.zones_title)}${Chars.COLON}${Chars.SPACE}${getZonesListDescription(zones, stringProvider)}"
)

private fun getZonesListDescription(zones: List<String>, stringProvider: StringProvider): String = zones.map {
    when (it) {
        "literacka" -> R.string.zone_literature
        "komiksowa" -> R.string.zone_comic
        "filmowa" -> R.string.zone_movie
        "naukowa" -> R.string.zone_science
        "cosplay" -> R.string.zone_cosplay
        "muzyczna" -> R.string.zone_music
        else -> null
    }
}.fastFilterNotNull()
    .joinToString { stringProvider.getString(it) }
