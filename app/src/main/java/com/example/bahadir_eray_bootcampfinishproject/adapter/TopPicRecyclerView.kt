package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopPicRecyclerViewRowBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class TopPicRecyclerView(val hotelsList: MutableList<HotelsModel>) :
    RecyclerView.Adapter<TopPicRecyclerView.TopPicViewHolder>() {
    class TopPicViewHolder(val view: TopPicRecyclerViewRowBinding) :
        RecyclerView.ViewHolder(view.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPicViewHolder {
        val view =
            TopPicRecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPicViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopPicViewHolder, position: Int) {
        holder.view.topImage.downloadFromUrl(
            hotelsList[position].images?.first()?.url,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.view.topTitle.text = hotelsList[position].description
    }

    override fun getItemCount(): Int {
        return hotelsList.count()
    }
}