package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bahadir_eray_bootcampfinishproject.data.model.HotelsModel

class HomeViewModel : ViewModel() {
    val hotels = MutableLiveData<List<HotelsModel>>()

    init {
        Log.v("HomeViewModel","Home")
    }

    override fun onCleared() {
        Log.v("OncLeared","Home")
    }
}