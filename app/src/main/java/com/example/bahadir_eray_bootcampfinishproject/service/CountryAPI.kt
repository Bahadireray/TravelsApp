package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //GET, POST
    //https://raw.githubusercontent.com/Bahadireray/ApiExample/main/ornekApi.json
    //BASE_URL -> https://raw.githubusercontent.com/
    //EXT -> Bahadireray/ApiExample/main/ornekApi.json

    @GET("Bahadireray/ApiExample/main/ornekApi.json")
    fun getCountries(): Single<List<CountryModel>>


}