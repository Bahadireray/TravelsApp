package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.HotelsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface HotelsAPI {
    @Headers("X-RapidAPI-Key: f731e4a216mshb17ea1868f5fe68p1254ccjsna3933ca79145", "X-RapidAPI-Host: hotels4.p.rapidapi.com")
    @GET("properties/get-hotel-photos")
    fun getData(): Call<ArrayList<HotelsModel>>
}