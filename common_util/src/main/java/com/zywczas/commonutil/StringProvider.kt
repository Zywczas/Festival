package com.zywczas.commonutil

import android.content.Context
import androidx.annotation.StringRes

class StringProvider(private val context: Context) {

    fun getString(@StringRes res: Int, vararg params: Any) = context.getString(res, *params)
}
