package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel

@Database(entities = [FavoriModel::class], version = 1)
abstract class FavoriDataBase : RoomDatabase() {
    abstract fun favoriDao(): FavoriDao
}