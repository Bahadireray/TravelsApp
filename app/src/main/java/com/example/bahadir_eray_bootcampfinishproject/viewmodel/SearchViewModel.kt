package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import com.example.bahadir_eray_bootcampfinishproject.service.CountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel : ViewModel() {
    val countriesModel = MutableLiveData<List<CountriesModel>>()
    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()


    override fun onCleared() {
    }

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CountriesModel>>() {
                    override fun onSuccess(t: List<CountriesModel>) {
                        countriesModel.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.v("Search", "Error " + e.localizedMessage)
                    }
                })
        )

    }


}