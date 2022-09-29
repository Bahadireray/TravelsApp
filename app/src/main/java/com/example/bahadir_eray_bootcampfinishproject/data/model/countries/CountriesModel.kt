package com.example.bahadir_eray_bootcampfinishproject.data.model.countries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountriesModel(
    @ColumnInfo(name = "data")
    @SerializedName("data")
    val `data`: Data
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}