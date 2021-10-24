package com.big.myweather.presentation.ui.adapter.holders

import com.big.myweather.databinding.ViewDailyWeatherReportBinding
import com.big.myweather.presentation.model.WeatherDailyReportUiModel

internal class WeatherDailyViewHolder(
    private val binding: ViewDailyWeatherReportBinding
) : BaseViewHolder(binding.root) {

    fun bindView(uiModel: WeatherDailyReportUiModel) {
        binding.run {
            dateTextView.text = uiModel.dateText
            humidityTextView.text = uiModel.humidityText
            minMaxTemperatureTextView.text = uiModel.minMaxTemperatureText
        }
    }
}