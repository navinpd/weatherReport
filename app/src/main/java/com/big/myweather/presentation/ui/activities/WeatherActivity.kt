package com.big.myweather.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.big.myweather.R
import com.big.myweather.databinding.ActivityWeatherBinding
import com.big.myweather.util.viewBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

internal class WeatherActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val binding by viewBinding(ActivityWeatherBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }


    override fun androidInjector(): AndroidInjector<Any> {
        TODO("Not yet implemented")
    }

}