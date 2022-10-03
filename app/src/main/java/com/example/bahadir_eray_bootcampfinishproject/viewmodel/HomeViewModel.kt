package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.service.HoteslAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val hoteslAPIService = HoteslAPIService()
    private val disposable = CompositeDisposable()
    val hotelsModel = MutableLiveData<List<HotelsModel>>()
    val filtrelHotelsModel = MutableLiveData<List<HotelsModel>>()

    fun setFilter(filter: String) {
        if (filter == "all") {
            filtrelHotelsModel.value = hotelsModel.value
        } else {
            filtrelHotelsModel.value = hotelsModel.value?.filter {
                it.category == filter
            }
        }
    }

    fun getDataFromAPI() {
        disposable.add(
            hoteslAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<HotelsModel>>() {
                    override fun onSuccess(t: List<HotelsModel>) {
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


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
