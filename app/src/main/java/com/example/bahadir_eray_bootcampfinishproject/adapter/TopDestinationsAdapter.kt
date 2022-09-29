package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class TopDestinationsAdapter(val countriesList: ArrayList<CountriesModel>) :
    RecyclerView.Adapter<TopDestinationsAdapter.TopCountriesViewHolder>() {
    private lateinit var context: Context

    class TopCountriesViewHolder(var topDestinastionItemBinding: TopDestinastionItemBinding) :
        RecyclerView.ViewHolder(topDestinastionItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCountriesViewHolder {
        val topDestinastionItemBinding =
            TopDestinastionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopCountriesViewHolder(topDestinastionItemBinding)
    }

    override fun onBindViewHolder(holder: TopCountriesViewHolder, position: Int) {
        holder.topDestinastionItemBinding.topImageItem.downloadFromUrl(
            countriesList[position].data.flagImageUri,
            placeholderProgressBar(holder.itemView.context)
        )
    }

    override fun getItemCount(): Int {
        return countriesList.count()
    }

    fun updateCountriesList(newCountriesModel: List<CountriesModel>) {
        countriesList.clear()
        countriesList.addAll(newCountriesModel)
        notifyDataSetChanged()
    }

}