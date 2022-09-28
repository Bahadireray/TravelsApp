package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.TopDestinastionItemBinding

class TopDestinationsAdapter(var countriesList: ArrayList<CountriesModel>) :
    RecyclerView.Adapter<TopDestinationsAdapter.TopCountriesViewHolder>() {
    private lateinit var context: Context

    class TopCountriesViewHolder(var topDestinastionItemBinding: TopDestinastionItemBinding) :
        RecyclerView.ViewHolder(topDestinastionItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCountriesViewHolder {
        val topDestinastionItemBinding =
            TopDestinastionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopCountriesViewHolder(topDestinastionItemBinding)
    }

    override fun onBindViewHolder(holder: TopCountriesViewHolder, position: Int) {
        
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}