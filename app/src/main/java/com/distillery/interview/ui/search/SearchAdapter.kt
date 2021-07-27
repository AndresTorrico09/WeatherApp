package com.distillery.interview.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.distillery.interview.data.models.City
import com.distillery.interview.databinding.ItemSearchResultBinding

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private val items = ArrayList<City>()

    fun setItems(items: ArrayList<City>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding: ItemSearchResultBinding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(items[position])
}

class SearchViewHolder(private val itemBinding: ItemSearchResultBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var city: City

    @SuppressLint("SetTextI18n")
    fun bind(item: City) {
        this.city = item
        itemBinding.textViewCityName.text = item.name
    }
}
