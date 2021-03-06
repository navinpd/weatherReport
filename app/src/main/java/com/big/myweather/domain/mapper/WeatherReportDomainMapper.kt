package com.big.myweather.domain.mapper

import com.big.myapplication.commons.Mapper
import com.big.myweather.data.remote.model.WeatherReportApiModel
import com.big.myweather.domain.model.WeatherReport
import javax.inject.Inject

internal class WeatherReportDomainMapper @Inject constructor() :
    Mapper<WeatherReportApiModel, WeatherReport> {
    override fun map(from: WeatherReportApiModel): WeatherReport =
        WeatherReport(
            time = from.time,
            summary = from.summary,
            icon = from.icon,
            temperature = from.temperature,
            apparentTemperature = from.apparentTemperature,
            dewPoint = from.dewPoint,
            humidity = from.humidity,
            pressure = from.pressure,
            windSpeed = from.windSpeed,
            windGust = from.windGust,
            windBearing = from.windBearing,
            cloudCover = from.cloudCover,
            uvIndex = from.uvIndex,
            visibility = from.visibility,
            ozone = from.ozone,
            precipIntensity = from.precipIntensity,
            precipProbability = from.precipProbability,
            sunriseTime = from.sunriseTime,
            sunsetTime = from.sunsetTime,
            moonPhase = from.moonPhase,
            precipIntensityMax = from.precipIntensityMax,
            precipIntensityMaxTime = from.precipIntensityMaxTime,
            precipType = from.precipType,
            temperatureHigh = from.temperatureHigh,
            temperatureHighTime = from.temperatureHighTime,
            temperatureLow = from.temperatureLow,
            temperatureLowTime = from.temperatureLowTime,
            apparentTemperatureHigh = from.apparentTemperatureHigh,
            apparentTemperatureHighTime = from.apparentTemperatureHighTime,
            apparentTemperatureLow = from.apparentTemperatureLow,
            apparentTemperatureLowTime = from.apparentTemperatureLowTime,
            windGustTime = from.windGustTime,
            uvIndexTime = from.uvIndexTime,
            temperatureMin = from.temperatureMin,
            temperatureMinTime = from.temperatureMinTime,
            temperatureMax = from.temperatureMax,
            temperatureMaxTime = from.temperatureMaxTime,
            apparentTemperatureMin = from.apparentTemperatureMin,
            apparentTemperatureMinTime = from.apparentTemperatureMinTime,
            apparentTemperatureMax = from.apparentTemperatureMax,
            apparentTemperatureMaxTime = from.apparentTemperatureMaxTime
        )
}