package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import io.reactivex.Single
import retrofit2.http.GET

interface TravelsAPI {

    //https://raw.githubusercontent.com/Bahadireray/ApiExample/main/projectApi.json

    @GET("Bahadireray/ApiExample/main/projectApi.json")
    fun getHotels(): Single<List<TravelsModel>>

}