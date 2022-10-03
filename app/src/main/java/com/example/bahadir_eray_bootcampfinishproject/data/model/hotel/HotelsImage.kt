package com.example.bahadir_eray_bootcampfinishproject.data.model.hotel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class HotelsImage(

    @ColumnInfo(name = "altText")
    @SerializedName("altText")
    val altText: String?,

    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: Int?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?,

    @ColumnInfo(name = "width")
    @SerializedName("width")
    val width: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}