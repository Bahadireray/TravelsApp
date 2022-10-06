package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.room.Room
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDao
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var favoriDao: FavoriDao
    private lateinit var favoriDataBase: FavoriDataBase
    private val mDisposable = CompositeDisposable()

    fun getDatabase(favoriModel: FavoriModel) {
        favoriDataBase =
            Room.databaseBuilder(getApplication(), FavoriDataBase::class.java, "Favories")
                .allowMainThreadQueries()
                .build()
        favoriDao = favoriDataBase.favoriDao()

        mDisposable.add(
            favoriDao.insert(favoriModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )

    }

    private fun handleResponse() {
        Toast.makeText(getApplication(), "Kayıt Edildi", Toast.LENGTH_SHORT).show()
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}