package com.example.bahadir_eray_bootcampfinishproject.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface FavoriDao {

    @Query("SELECT * FROM FavoriModel")
    fun getFavoriAll(): Flowable<List<FavoriModel>>

    @Insert
    fun insert(favoriModel: FavoriModel): Completable

    @Delete
    fun delete(favoriModel: FavoriModel): Completable
}