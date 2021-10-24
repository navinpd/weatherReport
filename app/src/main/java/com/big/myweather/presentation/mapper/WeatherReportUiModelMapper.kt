package com.big.myweather.presentation.mapper

import com.big.myapplication.commons.Mapper
import com.big.myweather.R
import com.big.myweather.domain.model.Weather
import com.big.myweather.domain.model.WeatherReportList
import com.big.myweather.presentation.model.*
import com.big.myweather.util.*
import javax.inject.Inject

internal class WeatherReportUiModelMapper @Inject constructor(
    private val stringLocalizer: StringLocalizer
) : Mapper<Weather, List<WeatherBaseUiModel>> {
    override fun map(from: Weather): List<WeatherBaseUiModel> {
        val list = arrayListOf(
            WeatherReportUiModel(
                timezone = from.timeZone,
                dateText = from.currentWeather.time.toDate(),
                summary = from.currentWeather.summary,
                temperature = from.currentWeather.temperature?.toCelsius() ?: "",
                minMaxTemperature = "${from.currentWeather.temperatureMax} / ${from.currentWeather.temperatureMin}",
                icon = from.currentWeather.icon.toWeatherIconDrawable(),
                hourlyReportList = from.hourlyWeatherReportList.toHourlyReport()
            ),
            WeatherReportMoreUiModel(
                uvText = stringLocalizer.getString(R.string.uv_index),
                uvValue = from.currentWeather.uvIndex.toString(),
                windText = stringLocalizer.getString(R.string.wind),
                windValue = "${from.currentWeather.windSpeed} km/hr",
                icon = from.currentWeather.icon.toWeatherIconDrawable(),
                humidityText = stringLocalizer.getString(R.string.humidity),
                humidityValue = "${(from.currentWeather.humidity * 100).toInt()} %",
                dewPointText = stringLocalizer.getString(R.string.dew_point),
                dewPointValue = from.currentWeather.dewPoint.toString(),
                visibilityText = stringLocalizer.getString(R.string.visibility),
                visibilityValue = from.currentWeather.visibility.toString(),
                ozoneText = stringLocalizer.getString(R.string.ozone),
                ozoneValue = from.currentWeather.ozone.toString()
            ),
        )
        list.addAll(from.dailyWeatherReportList.toDailyWeatherReport())
        return list
    }


    private fun WeatherReportList.toDailyWeatherReport(): List<WeatherDailyReportUiModel> {
        return this.weatherReport.map {
            WeatherDailyReportUiModel(
                dateText = it.time.toDayOfWeek(),
                humidityText = "${(it.humidity * 100).toInt()} %",
                minMaxTemperatureText = "${it.temperatureMax?.toCelsius() ?: ""} / ${it.temperatureMin?.toCelsius() ?: ""}",
                icon = it.icon.toWeatherIconDrawable()
            )
        }
    }

    private fun WeatherReportList.toHourlyReport(): List<WeatherHourlyReportUiModel> {
        return this.weatherReport.map {
            WeatherHourlyReportUiModel(
                timeText = it.time.toHourTime(),
                temperatureText = it.temperature?.toCelsius() ?: "",
                humidityText = "${(it.humidity * 100).toInt()} %",
                icon = it.icon.toWeatherIconDrawable()
            )
        }
    }
}