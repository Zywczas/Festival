package com.zywczas.commonutil

import androidx.annotation.StringRes

sealed class Resource<T> {

    class Success<T>(val data: T) : Resource<T>()

    class Error<T>(@StringRes val message: Int) : Resource<T>()

}
