package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.DealsItemsBinding

class DealsAdapter(val hotelsList: ArrayList<HotelsModel>) :
    RecyclerView.Adapter<DealsAdapter.DealsViewHolder>() {
    private lateinit var context: Context

    class DealsViewHolder(val dealsBinding: DealsItemsBinding) :
        RecyclerView.ViewHolder(dealsBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return hotelsList.count()
    }

}