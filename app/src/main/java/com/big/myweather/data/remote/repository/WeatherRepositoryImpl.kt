package com.big.myweather.data.remote.repository

import android.app.Application
import com.big.myapplication.commons.AssetFileLoader
import com.big.myapplication.commons.JsonParser
import com.big.myweather.data.remote.api.WeatherApi
import com.big.myweather.data.remote.model.WeatherApiModel
import com.big.myweather.domain.repository.WeatherRepository
import io.reactivex.Observable
import java.lang.Exception
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val app: Application,
    private val assetFileLoader: AssetFileLoader,
    private val jsonParser: JsonParser
) : WeatherRepository {

    companion object {
        const val IS_CACHED_RESPONSE = false
        const val WEATHER_JSON = "weather.json"
    }

    override fun getWeather(latitude: Double, longitude: Double): Observable<WeatherApiModel> {
        if (IS_CACHED_RESPONSE) {
            val jsonString = assetFileLoader.loadFileAsStream(app, WEATHER_JSON)
                .bufferedReader()
                .readText()
            return try {
                val weaApiModel = jsonParser.parse(jsonString, WeatherApiModel::class.java)
                Observable.just(weaApiModel)
            } catch (e: Exception) {
                Observable.error(Throwable("Error during parsing local JSON"))
            }
        }

        return weatherApi.getWeather(latitude, longitude)
    }
}