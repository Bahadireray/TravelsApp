package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.HotelsDatebase
import com.example.bahadir_eray_bootcampfinishproject.service.HoteslAPIService
import com.example.bahadir_eray_bootcampfinishproject.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val hoteslAPIService = HoteslAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    val hotelsModel = MutableLiveData<List<HotelsModel>>()
    private var refreshTime = 0.1 * 60 * 1000 * 1000 * 1000L

    fun refreshDataAll() {
        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLiteAll()
        } else {
            getDataFromAPI()
        }

    }

    fun getDataFromSQLiteAll() {
        launch {
            val hotels = HotelsDatebase(getApplication()).hotelsDao().getAllHotels()
            showCountriesAll(hotels)
            Toast.makeText(getApplication(), "Countries From SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataFromAPI() {
        disposable.add(
            hoteslAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<HotelsModel>>() {
                    override fun onSuccess(t: List<HotelsModel>) {
                        //   storeSQLiteAll(t)
                        Toast.makeText(getApplication(), "Suscess Home", Toast.LENGTH_SHORT)
                            .show()
                        hotelsModel.value = t
                        Log.v("Home", hotelsModel.toString())
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(), "Error Home", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        )
    }


    private fun showCountriesAll(hotelsList: List<HotelsModel>) {
        hotelsModel.value = hotelsList
    }

    private fun storeSQLiteAll(list: List<HotelsModel>) {

        launch {
            val dao = HotelsDatebase(getApplication()).hotelsDao()
            dao.deleteAllHotel()
            val listLong = dao.insertAll(*list.toTypedArray())  //List-> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountriesAll(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
