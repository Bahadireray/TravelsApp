package com.example.bahadir_eray_bootcampfinishproject.data.model.travel

data class TravelsModel(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<TravelsImage>?,
    val isBookmark: Boolean,
    val title: String
)