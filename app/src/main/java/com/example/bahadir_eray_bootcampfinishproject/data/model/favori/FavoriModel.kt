package com.example.bahadir_eray_bootcampfinishproject.data.model.favori

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriModel(
    @ColumnInfo(name = "city")
    val city: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "images")
    val images: String?,
    @ColumnInfo(name = "title")
    val title: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}