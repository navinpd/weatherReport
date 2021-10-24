package com.big.myweather.presentation.ui.adapter.holders

import android.widget.AutoCompleteTextView
import androidx.annotation.DrawableRes
import com.big.myweather.R
import com.big.myweather.databinding.ViewWeatherMoreItemBinding
import com.big.myweather.presentation.model.WeatherReportMoreUiModel
import com.big.myweather.util.leftDrawable

internal class WeatherMoreViewHolder(private val binding: ViewWeatherMoreItemBinding) :
    BaseViewHolder(binding.root) {

    fun bindView(uiModel: WeatherReportMoreUiModel) {
        binding.run {
            uvIndexLayout.itemTitleTextView.text = uiModel.uvText
            uvIndexLayout.itemValueTextView.text = uiModel.uvValue
            uvIndexLayout.itemTitleTextView.leftDrawable(R.drawable.ic_uv_index)

            windLayout.itemTitleTextView.text = uiModel.windText
            windLayout.itemValueTextView.text = uiModel.windValue
            windLayout.itemTitleTextView.leftDrawable(R.drawable.ic_wind)

            humidityLayout.itemTitleTextView.text = uiModel.humidityText
            humidityLayout.itemValueTextView.text = uiModel.humidityValue
            humidityLayout.itemTitleTextView.leftDrawable(R.drawable.ic_humidity)

            dewPointLayout.itemTitleTextView.text = uiModel.dewPointText
            dewPointLayout.itemValueTextView.text = uiModel.dewPointValue
            dewPointLayout.itemTitleTextView.leftDrawable(R.drawable.ic_dew_point)

            visibilityLayout.itemTitleTextView.text = uiModel.visibilityText
            visibilityLayout.itemValueTextView.text = uiModel.visibilityValue
            visibilityLayout.itemTitleTextView.leftDrawable(R.drawable.ic_visibility)

            ozoneLayout.itemTitleTextView.text = uiModel.ozoneText
            ozoneLayout.itemValueTextView.text = uiModel.ozoneValue
            ozoneLayout.itemTitleTextView.leftDrawable(R.drawable.ic_ozone)
        }
    }
}

