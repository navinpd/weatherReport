package com.big.myweather.util

import androidx.annotation.StringRes

interface StringLocalizer {
    fun getString(@StringRes id: Int): String
}