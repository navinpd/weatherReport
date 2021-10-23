package com.big.myweather.util

import android.app.Application
import javax.inject.Inject

class StringLocalizerImpl @Inject constructor(private val application: Application) : StringLocalizer {
    override fun getString(id: Int) = application.getString(id)
}