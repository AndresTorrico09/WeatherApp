package com.distillery.interview.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.distillery.interview.data.models.HitsItem
import com.distillery.interview.databinding.ItemSearchResultBinding

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private val items = ArrayList<HitsItem>()

    fun setItems(items: ArrayList<HitsItem>) {
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

    private lateinit var item: HitsItem

    @SuppressLint("SetTextI18n")
    fun bind(item: HitsItem) {
        this.item = item
        itemBinding.textViewCityName.text = item.localeNames.default.first().toString()
    }
}
