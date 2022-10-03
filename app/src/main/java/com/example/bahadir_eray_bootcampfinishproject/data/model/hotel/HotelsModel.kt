package com.example.bahadir_eray_bootcampfinishproject.data.model.hotel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class HotelsModel(
    @ColumnInfo(name = "category")
    @SerializedName("category")
    val category: String,

    @ColumnInfo(name = "city")
    @SerializedName("city")
    val city: String,

    @ColumnInfo(name = "country")
    @SerializedName("country")
    val country: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "images")
    @SerializedName("images")
    val images: List<HotelsImage>?,

    @ColumnInfo(name = "isBookmark")
    @SerializedName("isBookmark")
    val isBookmark: Boolean,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}