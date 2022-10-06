package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.bahadir_eray_bootcampfinishproject.adapter.BookmarkAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDao
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class TripViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var favoriDao: FavoriDao
    private lateinit var favoriDataBase: FavoriDataBase
    private val mDisposable = CompositeDisposable()
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var favoriList: List<FavoriModel>

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