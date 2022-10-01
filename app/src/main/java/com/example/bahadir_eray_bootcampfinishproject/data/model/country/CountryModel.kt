package com.example.bahadir_eray_bootcampfinishproject.data.model.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountryModel(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName: String?,
    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion: String?,
    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String?,
    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}
