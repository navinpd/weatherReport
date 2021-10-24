package com.big.myweather.domain.mapper

import com.big.myapplication.commons.Mapper
import com.big.myweather.data.remote.model.WeatherApiModel
import com.big.myweather.domain.model.Weather
import javax.inject.Inject

internal class WeatherDomainMapper @Inject constructor(
    private val weatherReportDomainMapper: WeatherReportDomainMapper,
    private val weatherReportListDomainMapper: WeatherReportListDomainMapper
) : Mapper<WeatherApiModel, Weather> {
    override fun map(from: WeatherApiModel) = Weather(
        latitude = from.latitude,
        longitude = from.longitude,
        timeZone = from.timezone,
        currentWeather = from.currentWeather.let { weatherReportDomainMapper.map(it) },
        hourlyWeatherReportList = from.hourlyWeatherReportList.let { weatherReportListDomainMapper.map(it) },
        dailyWeatherReportList = from.dailyWeatherReportList.let { weatherReportListDomainMapper.map(it) }
    )


}