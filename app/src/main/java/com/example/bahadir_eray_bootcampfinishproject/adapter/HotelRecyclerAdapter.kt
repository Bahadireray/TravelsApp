package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.data.model.HotelsModel

class HotelRecyclerAdapter(val hotelsList: ArrayList<HotelsModel>) :
    RecyclerView.Adapter<HotelRecyclerAdapter.HotelViewHolder>() {
    class HotelViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.detalsImageItem);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deals_items, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
       // val hotelsList = hotelsList.get(position)
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