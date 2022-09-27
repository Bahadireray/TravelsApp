package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.data.model.HotelsModel

class HotelRecyclerAdapter(val hotelsList: ArrayList<HotelsModel>) :
    RecyclerView.Adapter<HotelRecyclerAdapter.HotelViewHolder>() {
    class HotelViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deals_items, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return hotelsList.size
    }

    fun updateHotelsList(newHotelsList:List<HotelsModel>){
        hotelsList.clear()
        hotelsList.addAll(newHotelsList)
        notifyDataSetChanged()
    }
}