package com.big.myweather.domain.mapper

import com.big.myapplication.commons.Mapper
import com.big.myweather.data.remote.model.WeatherReportListApiModel
import com.big.myweather.domain.model.WeatherReportList
import javax.inject.Inject

internal class WeatherReportListDomainMapper @Inject constructor(
    private val weatherReportDomainMapper: WeatherReportDomainMapper
) : Mapper<WeatherReportListApiModel, WeatherReportList> {
    override fun map(from: WeatherReportListApiModel) =
        WeatherReportList(
            summary = from.summary,
            icon = from.icon,
            weatherReport = from.weatherReport.map { weatherReportDomainMapper.map(it) }
        )
}