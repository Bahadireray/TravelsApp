package com.example.bahadir_eray_bootcampfinishproject.data.model.hotels

data class HotelImage(
    val baseUrl: String,
    val imageId: Int,
    val mediaGUID: String,
    val sizes: List<Size>,
    val trackingDetails: TrackingDetails
)