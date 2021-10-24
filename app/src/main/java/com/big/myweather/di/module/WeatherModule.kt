package com.big.myweather.di.module

import android.app.Application
import com.big.myapplication.commons.AssetFileLoader
import com.big.myapplication.commons.JsonParser
import com.big.myweather.data.remote.api.WeatherApi
import com.big.myweather.data.remote.repository.WeatherRepositoryImpl
import com.big.myweather.di.builder.ViewModelFactoryBuilder
import com.big.myweather.domain.mapper.WeatherDomainMapper
import com.big.myweather.domain.mapper.WeatherReportDomainMapper
import com.big.myweather.domain.mapper.WeatherReportListDomainMapper
import com.big.myweather.domain.repository.WeatherRepository
import com.big.myweather.util.StringLocalizer
import com.big.myweather.util.StringLocalizerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryBuilder::class])
internal class WeatherModule {

    @Reusable
    @Provides
    fun provideJsonParser() = JsonParser()

    @Reusable
    @Provides
    fun providesAssetFileLoader() = AssetFileLoader()

    @Reusable
    @Provides
    fun providesWeatherReportDomainMapper() = WeatherReportDomainMapper()

    @Reusable
    @Provides
    fun providesWeatherReportListDomainMapper(
        weatherReportDomainMapper: WeatherReportDomainMapper
    ) : WeatherReportListDomainMapper {
        return WeatherReportListDomainMapper(
            weatherReportDomainMapper = weatherReportDomainMapper
        )
    }

    @Reusable
    @Provides
    fun providesWeatherDomainMapper(
        weatherReportDomainMapper: WeatherReportDomainMapper,
        weatherReportListDomainMapper: WeatherReportListDomainMapper
    ) : WeatherDomainMapper {
        return WeatherDomainMapper(
            weatherReportDomainMapper, weatherReportListDomainMapper
        )
    }


    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        app: Application,
        assetFileLoader: AssetFileLoader,
        jsonParser: JsonParser
    ) : WeatherRepository {
        return WeatherRepositoryImpl(
            weatherApi, app, assetFileLoader, jsonParser
        )
    }

    @Singleton
    @Provides
    fun provideStringLocalizer(application: Application): StringLocalizer {
        return StringLocalizerImpl(application)
    }


}