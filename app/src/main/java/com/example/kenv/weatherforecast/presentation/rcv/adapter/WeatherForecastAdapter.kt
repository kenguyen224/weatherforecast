package com.example.kenv.weatherforecast.presentation.rcv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kenv.weatherforecast.databinding.WeatherForecastItemViewBinding
import com.example.kenv.weatherforecast.presentation.uimodel.WeatherForecastUIModel

/**
 * Created by KeNV on 24,January,2021
 * VNG company,
 * HCM, Viet Nam
 */
class WeatherForecastAdapter : RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>() {

    private val data: MutableList<WeatherForecastUIModel> = mutableListOf()

    class ViewHolder(private val viewBinding: WeatherForecastItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bindView(item: WeatherForecastUIModel) {
            with(viewBinding) {
                tvDate.text = item.dateTime
                tvTemp.text = item.temperature
                tvPressure.text = item.pressure
                tvHumidity.text = item.humidity
                tvDescription.text = item.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        WeatherForecastItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(data[position])

    override fun getItemCount(): Int = data.size

    fun setData(data: List<WeatherForecastUIModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}
