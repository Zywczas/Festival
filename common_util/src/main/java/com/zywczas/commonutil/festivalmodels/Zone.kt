package com.zywczas.commonutil.festivalmodels

import androidx.annotation.StringRes
import com.zywczas.commonutil.R

enum class Zone(@StringRes val displayedName: Int) {

    Literature(R.string.zone_literature),
    Comic(R.string.zone_comic),
    Movie(R.string.zone_movie),
    Cosplay(R.string.zone_cosplay),
    Science(R.string.zone_science),
    Music(R.string.zone_music),
}
