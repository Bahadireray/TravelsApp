package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel

@Dao
interface HotelDao {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg hotels: HotelsModel): List<Long>
    //Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple hotels objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM hotelsmodel")
    suspend fun getAllHotels(): List<HotelsModel>

    @Query("SELECT * FROM hotelsmodel WHERE uuid = :hotelsId")
    suspend fun getHotel(hotelsId: Int): HotelsModel

    @Query("DELETE FROM HotelsModel")
    suspend fun deleteAllHotel()

}