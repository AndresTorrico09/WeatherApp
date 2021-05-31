package com.distillery.interview.ui.daily_weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.distillery.interview.R
import com.distillery.interview.data.models.Daily
import com.distillery.interview.databinding.ItemDailyWeatherBinding
import com.distillery.interview.util.toDate

class DailyWeatherAdapter : RecyclerView.Adapter<DailyViewHolder>() {

    private val items = ArrayList<Daily>()

    fun setItems(items: ArrayList<Daily>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val binding: ItemDailyWeatherBinding =
            ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) =
        holder.bind(items[position])
}

class DailyViewHolder(private val itemBinding: ItemDailyWeatherBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var daily: Daily

    @SuppressLint("SetTextI18n")
    fun bind(item: Daily) {
        this.daily = item
        itemBinding.tvDate.text = item.dt.toDate()
        itemBinding.tvTempDesc.text = item.weather.firstOrNull()?.main ?: ""
        itemBinding.tvTempMax.text =
            itemBinding.root.resources.getString(R.string.temp_text, item.temp.max.toString())
        itemBinding.tvTempMin.text =
            itemBinding.root.resources.getString(R.string.temp_text, item.temp.min.toString())
    }
}
