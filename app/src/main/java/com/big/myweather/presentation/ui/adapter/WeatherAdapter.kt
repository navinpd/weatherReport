package com.big.myweather.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.big.myweather.databinding.ViewDailyWeatherReportBinding
import com.big.myweather.databinding.ViewWeatherHeaderItemBinding
import com.big.myweather.databinding.ViewWeatherMoreItemBinding
import com.big.myweather.presentation.model.WeatherBaseUiModel
import com.big.myweather.presentation.model.WeatherDailyReportUiModel
import com.big.myweather.presentation.model.WeatherReportMoreUiModel
import com.big.myweather.presentation.model.WeatherReportUiModel
import com.big.myweather.presentation.ui.adapter.holders.BaseViewHolder
import com.big.myweather.presentation.ui.adapter.holders.WeatherDailyViewHolder
import com.big.myweather.presentation.ui.adapter.holders.WeatherHeaderViewHolder
import com.big.myweather.presentation.ui.adapter.holders.WeatherMoreViewHolder

internal class WeatherAdapter constructor(
    private val weatherReport: MutableList<WeatherBaseUiModel> = mutableListOf()
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            WeatherBaseUiModel.ITEM_TYPE_MORE_ITEM -> {
                WeatherMoreViewHolder(
                    ViewWeatherMoreItemBinding.inflate(inflater, parent, false)
                )
            }
            WeatherBaseUiModel.ITEM_TYPE_DAILY_REPORT_ITEM -> {
                WeatherDailyViewHolder(
                    ViewDailyWeatherReportBinding.inflate(inflater, parent, false)
                )
            }
            else -> {
                WeatherHeaderViewHolder(
                    ViewWeatherHeaderItemBinding.inflate(inflater, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val uiModel = weatherReport[position]
        when (holder) {
            is WeatherHeaderViewHolder -> {
                holder.bindView(uiModel as WeatherReportUiModel)
            }
            is WeatherMoreViewHolder -> {
                holder.bindView(uiModel as WeatherReportMoreUiModel)
            }
            is WeatherDailyViewHolder -> {
                holder.bindView(uiModel as WeatherDailyReportUiModel)
            }
        }
    }

    fun update(weatherReport: List<WeatherBaseUiModel>) {
        this.weatherReport.clear()
        this.weatherReport.addAll(weatherReport)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() = weatherReport.size

    override fun getItemViewType(position: Int): Int {
        return weatherReport[position].type
    }
}