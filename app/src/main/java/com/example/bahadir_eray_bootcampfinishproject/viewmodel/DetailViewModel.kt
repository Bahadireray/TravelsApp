package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDao
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    private lateinit var favoriDao: FavoriDao
    private lateinit var favoriDataBase: FavoriDataBase
    private val disposable = CompositeDisposable()

    fun saveDatabase(favoriModel: FavoriModel) {
        launch {
            favoriDataBase =
                Room.databaseBuilder(getApplication(), FavoriDataBase::class.java, "Favories")
                    .allowMainThreadQueries()
                    .build()
            favoriDao = favoriDataBase.favoriDao()

            disposable.add(
                favoriDao.insert(favoriModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(getApplication(), "Kayıt Edildi", Toast.LENGTH_SHORT).show()
                    }, {
                        Log.e("Error Detail GetData", it.message.toString())
                    })
            )
        }
    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
