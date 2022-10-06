package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class SeeAllAdapter(val travelsList: MutableList<TravelsModel>, private val listener: Listener) :
    RecyclerView.Adapter<SeeAllAdapter.SeeAllViewHolder>() {
    interface Listener {
        fun onItemClick(travelsModel: TravelsModel)
    }

    class SeeAllViewHolder(val seeAllview: TopDestinastionItemBinding) :
        RecyclerView.ViewHolder(seeAllview.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val view =
            TopDestinastionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeeAllAdapter.SeeAllViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.seeAllview.imgTitle.text = travelsList[position].title
        holder.seeAllview.topImageItem.downloadFromUrl(
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