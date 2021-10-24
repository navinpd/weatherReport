package com.big.myweather.domain.usecase

import com.big.myapplication.commons.RxUseCase
import com.big.myweather.domain.mapper.WeatherDomainMapper
import com.big.myweather.domain.model.Location
import com.big.myweather.domain.model.Weather
import com.big.myweather.domain.repository.WeatherRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class GetWeatherUseCase @Inject constructor(
    val repository: WeatherRepository,
    val weatherDomainMapper : WeatherDomainMapper
) : RxUseCase<Location, Weather> {
    override fun run(params: Location?): Observable<Weather> {
        checkNotNull(params) { "location cannot be null" }

        return repository.getWeather(
            latitude = params.latitude,
            longitude = params.longitude
        )
            .map { weatherDomainMapper.map(it) }
            .subscribeOn(Schedulers.io())
    }
}