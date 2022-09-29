package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.hotels.HotelsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelsAPI {
    companion object {
        const val API_HOST = "hotels4.p.rapidapi.com"
        const val API_KEY = "f731e4a216mshb17ea1868f5fe68p1254ccjsna3933ca79145"
    }

    @GET("properties/get-hotel-photos")
    fun getData(
        @Query("API_KEY") apiKey: String,
        @Query("API_HOST") apiHost: String
    ): Single<List<HotelsModel>>
}