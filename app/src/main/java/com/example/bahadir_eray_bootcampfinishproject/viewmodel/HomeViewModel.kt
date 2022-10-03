package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.hotel.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.service.HoteslAPIService
import com.example.bahadir_eray_bootcampfinishproject.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val hoteslAPIService = HoteslAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    val hotelsModel = MutableLiveData<List<HotelsModel>>()
    private var refreshTime = 0.1 * 60 * 1000 * 1000 * 1000L
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

    fun refreshDataAll() {
        getDataFromAPI()

    }

    fun getDataFromAPI() {
        disposable.add(
            hoteslAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<HotelsModel>>() {
                    override fun onSuccess(t: List<HotelsModel>) {

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


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
