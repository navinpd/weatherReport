package com.big.myweather.di.builder

import androidx.lifecycle.ViewModel
import com.big.myapplication.commons.ViewModelKey
import com.big.myweather.presentation.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: WeatherViewModel): ViewModel
}