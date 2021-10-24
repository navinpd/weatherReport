package com.big.myweather.di.component

import android.app.Application
import com.big.myweather.di.WeatherApp
import com.big.myweather.di.builder.ActivityBuilder
import com.big.myweather.di.module.NetworkModule
import com.big.myweather.di.module.WeatherModule
import com.big.myweather.presentation.WeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        NetworkModule::class,
        WeatherModule::class]
)

internal interface WeatherAppComponent : AndroidInjector<WeatherApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: WeatherApp): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): WeatherAppComponent
    }

}