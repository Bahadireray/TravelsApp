package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel

@Dao
interface CountryDao {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: CountryModel): List<Long>

    //Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple country objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM countrymodel")
    suspend fun getAllCountries(): List<CountryModel>

    @Query("SELECT * FROM countrymodel WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): CountryModel

    @Query("DELETE FROM countrymodel")
    suspend fun deleteAllCountries()

}