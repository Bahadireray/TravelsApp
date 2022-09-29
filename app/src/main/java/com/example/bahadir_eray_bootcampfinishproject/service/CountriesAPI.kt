package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesAPI {
    companion object {
        const val API_HOST = "wft-geo-db.p.rapidapi.com"
        const val API_KEY = "f731e4a216mshb17ea1868f5fe68p1254ccjsna3933ca79145"

    }

    @GET("v1/geo/countries/US")
    fun getCountriesData(
        @Query("API_KEY") apiKey: String,
        @Query("API_HOST") apiHost: String
    ): Single<List<CountriesModel>>
}