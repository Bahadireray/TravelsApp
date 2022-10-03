package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel


@Database(entities = arrayOf(HotelsModel::class), version = 2)
abstract class HotelsDatebase : RoomDatabase() {
    abstract fun hotelsDao(): HotelDao

    companion object {

        @Volatile
        private var instance: HotelsDatebase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, HotelsDatebase::class.java, "hotelsdatabase"
        ).build()
    }


}