package com.distillery.interview.ui.today_weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.distillery.interview.R
import com.distillery.interview.data.models.Hourly
import com.distillery.interview.databinding.ItemHourlyWeatherBinding
import com.distillery.interview.util.toTime

class HourlyWeatherAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<Hourly>()

    fun setItems(items: ArrayList<Hourly>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemHourlyWeatherBinding =
            ItemHourlyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(items[position])
}

class MovieViewHolder(private val itemBinding: ItemHourlyWeatherBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var movie: Hourly

    @SuppressLint("SetTextI18n")
    fun bind(item: Hourly) {
        this.movie = item
        itemBinding.tvHour.text = item.dt.toTime()
        itemBinding.tvTemp.text =
            itemBinding.root.resources.getString(R.string.temp_text, item.temp.toString())

    }
}
