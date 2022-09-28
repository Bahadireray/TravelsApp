package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bahadir_eray_bootcampfinishproject.data.model.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.service.HotelsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    val hotels = MutableLiveData<List<HotelsModel>>()
    private val hotelsService = HotelsService()
    private val disposable = CompositeDisposable()


    override fun onCleared() {
    }

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {

        Log.v("Test", "getDataFrom ViewModel")
        disposable.add(
            hotelsService.getHotels()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<HotelsModel>>() {
                    override fun onSuccess(t: List<HotelsModel>) {

                        Log.v("Test", t.toString())
                        hotels.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }


}
