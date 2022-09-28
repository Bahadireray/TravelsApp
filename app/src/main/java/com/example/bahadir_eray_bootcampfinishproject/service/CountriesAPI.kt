package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountriesAPI {
    @Headers(
        "X-RapidAPI-Key:" + "f731e4a216mshb17ea1868f5fe68p1254ccjsna3933ca79145",
        "X-RapidAPI-Host:" + "wft-geo-db.p.rapidapi.com"
    )
    @GET("v1/geo/countries/US")
    fun getCountriesData(): Single<List<CountriesModel>>
}