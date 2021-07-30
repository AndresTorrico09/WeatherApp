package com.distillery.interview.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.distillery.interview.data.models.GeoLocation
import com.distillery.interview.data.models.HitsItem
import com.distillery.interview.databinding.ItemSearchResultBinding

class SearchAdapter(private val listener: LocationClickable) :
    RecyclerView.Adapter<SearchViewHolder>() {

    interface LocationClickable {
        fun onClickLocation(loc: GeoLocation)
    }

    private val items = ArrayList<HitsItem>()

    fun setItems(items: ArrayList<HitsItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding: ItemSearchResultBinding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(items[position])
}

class SearchViewHolder(
    private val itemBinding: ItemSearchResultBinding,
    private val listener: SearchAdapter.LocationClickable,
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    init {
        itemBinding.rootView.setOnClickListener(this)
    }

    private lateinit var item: HitsItem

    @SuppressLint("SetTextI18n")
    fun bind(item: HitsItem) {
        this.item = item
        itemBinding.textViewCityName.text = item.localeNames.default.first().toString()
    }

    override fun onClick(v: View?) {
        listener.onClickLocation(GeoLocation(item.geoLocation.lat, item.geoLocation.lon))
    }
}
