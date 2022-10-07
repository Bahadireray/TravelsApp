package com.example.bahadir_eray_bootcampfinishproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.SaveBookmarkRowBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar

class BookmarkAdapter(val favoriList: List<FavoriModel>, private val listener: Listener) :
    RecyclerView.Adapter<BookmarkAdapter.FavoriViewHolder>() {

    interface Listener {
        fun onItemBookClick(favoriModel: FavoriModel)
    }

    class FavoriViewHolder(var view: SaveBookmarkRowBinding) :
        RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder {
        val view =
            SaveBookmarkRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkAdapter.FavoriViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {
        holder.view.typeText.text = favoriList[position].city
        holder.view.nearbyTitle.text = favoriList[position].title
        holder.view.nearbyImage.downloadFromUrl(
            favoriList[position].images,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            listener.onItemBookClick(favoriList[position])
        }
    }

    override fun getItemCount(): Int {
        return favoriList.count()
    }
}