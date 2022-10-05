package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopPicRecyclerViewRowBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class TopPicAdapter(val travelsList: MutableList<TravelsModel>, private val listener: Listener) :
    RecyclerView.Adapter<TopPicAdapter.TopPicViewHolder>() {

    interface Listener {
        fun onItemPicClick(travelsModel: TravelsModel)
    }

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
            travelsList[position].images?.first()?.url,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.view.topTitle.text = travelsList[position].title
        holder.view.topDescriptionText.text = travelsList[position].description
        holder.itemView.setOnClickListener {
            listener.onItemPicClick(travelsList[position])
        }
    }

    override fun getItemCount(): Int {
        return travelsList.count()
    }
}