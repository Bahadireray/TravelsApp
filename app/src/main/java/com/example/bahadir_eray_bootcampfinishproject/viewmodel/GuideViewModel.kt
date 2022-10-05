package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.service.TravelsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GuideViewModel(application: Application) : BaseViewModel(application) {
    private val travelsAPIService = TravelsAPIService()
    private val disposable = CompositeDisposable()
    val travelsModel = MutableLiveData<List<TravelsModel>>()
    val filtrelTravelsModel = MutableLiveData<List<TravelsModel>>()

    fun setFilter(filter: String) {
        if (filter == "all") {
            filtrelTravelsModel.value = travelsModel.value
        } else {
            filtrelTravelsModel.value = travelsModel.value?.filter {
                it.category == filter
            }
        }
    }

    fun getDataFromAPI() {
        disposable.add(
            travelsAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<TravelsModel>>() {
                    override fun onSuccess(t: List<TravelsModel>) {
                        travelsModel.value = t
                        Log.v("Guide", travelsModel.toString())
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(), "Error Home", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}