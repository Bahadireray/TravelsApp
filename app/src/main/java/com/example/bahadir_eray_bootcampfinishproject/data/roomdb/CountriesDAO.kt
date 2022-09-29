package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel

@Dao
interface CountriesDAO {
    /*Insert -> insert into
    suspend -> corutine, pause &resume
    varag -> object keyWord, multiple countries objects
    List<Long> -> primaryKey
     */
    @Insert
    suspend fun insertAll(vararg countries: CountriesModel): List<Long>

    @Query("SELECT * FROM countriesModel")
    suspend fun getAllCountries(): List<CountriesModel>

    @Query("SELECT * FROM countriesModel WHERE uuid = :countriesId")
    suspend fun getCountry(countriesId: Int): CountriesModel

    @Query("DELETE From countriesModel")
    suspend fun deleteAllCountries()

}