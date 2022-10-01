package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class TopDestinationsAdapter(val countryList: ArrayList<CountryModel>) :
    RecyclerView.Adapter<TopDestinationsAdapter.TopCountriesViewHolder>() {
    private lateinit var context: Context

    class TopCountriesViewHolder(var view: TopDestinastionItemBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCountriesViewHolder {
        val topDestinastionItemBinding =
            TopDestinastionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopCountriesViewHolder(topDestinastionItemBinding)
    }

    override fun onBindViewHolder(holder: TopCountriesViewHolder, position: Int) {

        holder.view.imgTitle.text = countryList[position].countryName
        holder.view.topImageItem.downloadFromUrl(
            countryList[position].imageUrl,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                countryList[position].countryName.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun getItemCount(): Int {
        return countryList.count()
    }

    fun updateCountriesList(newCountriesModel: List<CountryModel>) {
        countryList.clear()
        countryList.addAll(newCountriesModel)
         notifyDataSetChanged()
    }
}