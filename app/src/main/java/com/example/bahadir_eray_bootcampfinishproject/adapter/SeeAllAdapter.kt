package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.SeeAllRowBinding
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class SeeAllAdapter(val travelsList: MutableList<TravelsModel>, private val listener: Listener) :
    RecyclerView.Adapter<SeeAllAdapter.SeeAllViewHolder>() {
    interface Listener {
        fun onItemClick(travelsModel: TravelsModel)
    }

    class SeeAllViewHolder(val view: SeeAllRowBinding) :
        RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val view =
            SeeAllRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeeAllViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.view.seeAllTitle
            .text = travelsList[position].title
        holder.view.seeAllImg
            .downloadFromUrl(
            travelsList[position].images?.first()?.url,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            listener.onItemClick(travelsList[position])
        }
    }

    override fun getItemCount(): Int {
        return travelsList.count()
    }
}