package com.big.myweather.presentation.model

import androidx.annotation.DrawableRes

data class WeatherDailyReportUiModel(
    val dateText: String,
    val humidityText: String,
    val minMaxTemperatureText: String,
    @DrawableRes val icon: Int
) : WeatherBaseUiModel {

    override val type: Int
        get() = WeatherBaseUiModel.ITEM_TYPE_DAILY_REPORT_ITEM

}