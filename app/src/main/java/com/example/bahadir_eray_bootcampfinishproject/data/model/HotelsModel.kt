package com.example.bahadir_eray_bootcampfinishproject.data.model

data class HotelsModel(
    val featuredImageTrackingDetails: FeaturedImageTrackingDetails,
    val hotelId: Int,
    val hotelImages: List<HotelImage>,
    val propertyImageTrackingDetails: PropertyImageTrackingDetails,
    val roomImages: List<RoomImage>
)