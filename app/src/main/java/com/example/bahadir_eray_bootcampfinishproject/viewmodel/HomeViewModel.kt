package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotels.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.service.HotelsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : BaseViewModel(application) {
    val hotels = MutableLiveData<List<HotelsModel>>()
    private val hotelsService = HotelsService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {
        disposable.add(
            hotelsService.getHotels()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<HotelsModel>>() {
                    override fun onSuccess(t: List<HotelsModel>) {

                        Log.v("Test", t.toString())
                        hotels.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.v("Home", "Error " + e.localizedMessage)
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
