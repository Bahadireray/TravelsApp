package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import androidx.room.Room
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDao
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDataBase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class TripViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var favoriDao: FavoriDao
    private lateinit var favoriDataBase: FavoriDataBase
    private val mDisposable = CompositeDisposable()

    fun getDataBase() {
        launch {
            favoriDataBase =
                Room.databaseBuilder(getApplication(), FavoriDataBase::class.java, "Favories")
                    .allowMainThreadQueries()
                    .build()
            favoriDao = favoriDataBase.favoriDao()

        }
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}