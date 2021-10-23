package com.big.myweather.domain.usecase

import com.big.myweather.domain.mapper.WeatherDomainMapper
import com.big.myweather.domain.repository.WeatherRepository
import javax.inject.Inject

internal class GetWeatherUseCase @Inject constructor(
    val repository: WeatherRepository,
    val weatherDomainMapper : WeatherDomainMapper
) {
}