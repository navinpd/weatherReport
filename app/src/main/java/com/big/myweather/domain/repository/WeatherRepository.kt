package com.big.myweather.domain.repository

import com.big.myweather.data.remote.model.WeatherApiModel
import io.reactivex.Observable

internal interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double) : Observable<WeatherApiModel>
}