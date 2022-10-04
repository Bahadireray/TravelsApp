package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.NearbyAttractionsItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class NearbyAdapter(val travelsList: MutableList<TravelsModel>) :
    RecyclerView.Adapter<NearbyAdapter.NearbyViewHolder>() {
    private lateinit var context: Context

    class NearbyViewHolder(var nearbyAttractionsItemBinding: NearbyAttractionsItemBinding) :
        RecyclerView.ViewHolder(nearbyAttractionsItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyViewHolder {
        val nearbyAttractionsItemBinding =
            NearbyAttractionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NearbyViewHolder(nearbyAttractionsItemBinding)
    }

    override fun onBindViewHolder(holder: NearbyViewHolder, position: Int) {
        holder.nearbyAttractionsItemBinding.typeText.text = travelsList[position].category
        holder.nearbyAttractionsItemBinding.nearbyTitle.text = travelsList[position].city
        holder.nearbyAttractionsItemBinding.nearbyImage.downloadFromUrl(
            travelsList[position].images?.first()?.url,
            placeholderProgressBar(holder.itemView.context)
        )
    }


    override fun getItemCount(): Int {
        return travelsList.count()
    }

}