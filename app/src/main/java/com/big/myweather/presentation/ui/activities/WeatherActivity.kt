package com.big.myweather.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.big.myweather.R
import com.big.myweather.databinding.ActivityWeatherBinding
import com.big.myweather.di.WeatherApp
import com.big.myweather.presentation.ui.fragment.WeatherFragment
import com.big.myweather.util.bind
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
        WeatherApp.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        attachFragment()
    }

    private fun attachFragment() {
        WeatherFragment.newInstance().bind(this, R.id.containerFrameLayout)
    }

    override fun androidInjector() = androidInjector
}