package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel
import io.reactivex.schedulers.Schedulers


class HomeViewModel(application: Application) : BaseViewModel(application) {
    val hotels = MutableLiveData<List<CountryModel>>()

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {


    }

    override fun onCleared() {
        super.onCleared()
    }

}
