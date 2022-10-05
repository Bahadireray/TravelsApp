package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class TopDestinationsAdapter(
    val travelList: MutableList<TravelsModel>,
    private val listener: Listener
) :
    RecyclerView.Adapter<TopDestinationsAdapter.TopCountriesViewHolder>() {
    private lateinit var context: Context

    interface Listener {
        fun onItemTopClick(travelsModel: TravelsModel)
    }

    class TopCountriesViewHolder(val topDestinastionItemBinding: TopDestinastionItemBinding) :
        RecyclerView.ViewHolder(topDestinastionItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCountriesViewHolder {
        val topDestinastionItemBinding =
            TopDestinastionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopCountriesViewHolder(topDestinastionItemBinding)
    }


    override fun onBindViewHolder(holder: TopCountriesViewHolder, position: Int) {
        holder.topDestinastionItemBinding.imgTitle.text = travelList[position].title
        holder.topDestinastionItemBinding.topImageItem.downloadFromUrl(
            travelList[position].images?.first()?.url,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            listener.onItemTopClick(travelList[position])
        }
    }

    override fun getItemCount(): Int {
        return travelList.count()
    }
}