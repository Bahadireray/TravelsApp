package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<CountryModel>()
    fun getDataTopFrom() {

    }

    override fun onCleared() {
        super.onCleared()
    }
}