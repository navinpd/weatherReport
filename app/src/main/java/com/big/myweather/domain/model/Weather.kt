package com.big.myweather.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class Weather(
    val latitude: Double,
    val longitude: Double,
    val timeZone: String,
    val currentWeather: WeatherReport,
    val hourlyWeatherReportList: WeatherReportList,
    val dailyWeatherReportList: WeatherReportList
) : Parcelable