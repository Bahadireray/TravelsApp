package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.DealsItemsBinding
import com.example.bahadir_eray_bootcampfinishproject.databinding.MightNeedRowBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class MightAdapter(val travelList: MutableList<TravelsModel>) :
    RecyclerView.Adapter<MightAdapter.MightViewHolder>() {
    private lateinit var context: Context

    class MightViewHolder(val view: MightNeedRowBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MightViewHolder {
        val view =
            MightNeedRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MightViewHolder(view)
    }

    override fun onBindViewHolder(holder: MightViewHolder, position: Int) {
        holder.view.mightTitle.text = travelList[position].city
        holder.view.mightImage.downloadFromUrl(
            travelList[position].images?.first()?.url,
            placeholderProgressBar(holder.itemView.context)
        )
    }

    override fun getItemCount(): Int {
        return travelList.count()
    }


}