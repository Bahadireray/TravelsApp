package com.example.bahadir_eray_bootcampfinishproject.data.model.hotel

data class HotelsModel(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<HotelsImage>?,
    val isBookmark: Boolean,
    val title: String
)