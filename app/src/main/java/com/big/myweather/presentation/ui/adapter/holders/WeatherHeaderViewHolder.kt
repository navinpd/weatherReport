package com.big.myweather.presentation.ui.adapter.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.big.myweather.databinding.ViewWeatherHeaderItemBinding
import com.big.myweather.databinding.ViewWeatherHourlyItemBinding
import com.big.myweather.presentation.model.WeatherHourlyReportUiModel
import com.big.myweather.presentation.model.WeatherReportUiModel

internal class WeatherHeaderViewHolder(
    private val binding: ViewWeatherHeaderItemBinding
) : BaseViewHolder(binding.root) {
    private var isRecyclerViewLoaded = false

    fun bindView(uiModel: WeatherReportUiModel) {
        binding.run {
            timezoneTextView.text = uiModel.timezone
            dateTextView.text = uiModel.dateText
            temperatureTextView.text = uiModel.temperature
            summaryTextView.text = uiModel.summary
            weatherIconImageView.setImageResource(uiModel.icon)
            if (!isRecyclerViewLoaded)
                loadHourlyReport(uiModel.hourlyReportList)
        }
    }

    private fun loadHourlyReport(list: List<WeatherHourlyReportUiModel>) {
        this.isRecyclerViewLoaded = true
        binding.run {
            hourlyReportRecyclerView.adapter = HourWeatherReportAdapter(list)
        }
    }

}

class HourWeatherReportAdapter(
    private val list: List<WeatherHourlyReportUiModel>
) : RecyclerView.Adapter<WeatherHourlyReportViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHourlyReportViewHolder {
        return WeatherHourlyReportViewHolder(
            ViewWeatherHourlyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherHourlyReportViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount() = list.size
}
