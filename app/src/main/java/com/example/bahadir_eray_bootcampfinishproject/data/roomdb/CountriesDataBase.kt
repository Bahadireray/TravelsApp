package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel

@Database(entities = arrayOf(CountriesModel::class), version = 1)
abstract class CountriesDataBase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDAO

    //Singleton
    companion object {
        @Volatile
        private var instance: CountriesDataBase? = null
        private var lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CountriesDataBase::class.java, "countriesDataBase"
        ).build()
    }
}