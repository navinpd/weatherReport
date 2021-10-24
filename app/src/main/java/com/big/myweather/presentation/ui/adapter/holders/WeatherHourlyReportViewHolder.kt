package com.big.myweather.presentation.ui.adapter.holders

import com.big.myweather.databinding.ViewWeatherHourlyItemBinding
import com.big.myweather.presentation.model.WeatherHourlyReportUiModel

class WeatherHourlyReportViewHolder(
    private val binding: ViewWeatherHourlyItemBinding
) : BaseViewHolder(binding.root) {

    fun bindView(uiModel: WeatherHourlyReportUiModel) {
        binding.run {
            timeTextView.text = uiModel.timeText
            temperatureTextView.text = uiModel.temperatureText
            humidityTextView.text = uiModel.humidityText
            weatherIconImageView.setImageResource(uiModel.icon)
        }
    }

}