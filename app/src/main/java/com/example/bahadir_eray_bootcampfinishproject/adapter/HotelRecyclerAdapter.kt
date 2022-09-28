package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bahadir_eray_bootcampfinishproject.databinding.DealsItemsBinding
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotels.HotelsModel

class HotelRecyclerAdapter(val hotelsList: ArrayList<HotelsModel>) :
    RecyclerView.Adapter<HotelRecyclerAdapter.HotelViewHolder>() {
    private lateinit var context: Context

    class HotelViewHolder(val dealsItemsBinding: DealsItemsBinding) :
        RecyclerView.ViewHolder(dealsItemsBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val dealsItemsBinding =
            DealsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelViewHolder(dealsItemsBinding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {

        Glide.with(context)
            .load(hotelsList[position].hotelImages)
            .into(holder.dealsItemsBinding.detalsImageItem)
    }

    override fun getItemCount(): Int {
        return hotelsList.size
    }

    fun updateHotelsList(newHotelsList: List<HotelsModel>) {
        hotelsList.clear()
        hotelsList.addAll(newHotelsList)
        notifyDataSetChanged()
    }
}