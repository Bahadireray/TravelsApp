package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.CategoriRowBinding

class CategoriAdapter(val travelsList: MutableList<TravelsModel>) :
    RecyclerView.Adapter<CategoriAdapter.CategoriViewHolder>() {

    class CategoriViewHolder(val view: CategoriRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriViewHolder {
        val view =
            CategoriRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriViewHolder, position: Int) {
        holder.view.categoriTitle.text = travelsList[position].city
    }

    override fun getItemCount(): Int {
        return travelsList.count()
    }

}