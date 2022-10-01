package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.NearbyAttractionsItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar
import com.example.bahadir_eray_bootcampfinishproject.view.fragment.SearchFragment

class NearbyRecyclerView(val countryList: ArrayList<CountryModel>, searchFragment: SearchFragment) :
    RecyclerView.Adapter<NearbyRecyclerView.NearbyViewHolder>() {
    private lateinit var context: Context

    class NearbyViewHolder(var view: NearbyAttractionsItemBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyViewHolder {
        val nearbyAttractionsItemBinding =
            NearbyAttractionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NearbyViewHolder(nearbyAttractionsItemBinding)
    }

    override fun onBindViewHolder(holder: NearbyViewHolder, position: Int) {
        holder.view.nearbyTitle.text = countryList[position].countryName
        holder.view.nearbyImage.downloadFromUrl(
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountriesList(newCountriesModel: List<CountryModel>) {
        countryList.clear()
        countryList.addAll(newCountriesModel)
        notifyDataSetChanged()
    }
}