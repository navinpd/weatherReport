package com.big.myweather.di.builder

import com.big.myweather.presentation.ui.activities.WeatherActivity
import com.big.myweather.presentation.ui.fragment.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindWeatherActivity() : WeatherActivity

    @ContributesAndroidInjector
    abstract fun bindWeatherFragment() : WeatherFragment

}