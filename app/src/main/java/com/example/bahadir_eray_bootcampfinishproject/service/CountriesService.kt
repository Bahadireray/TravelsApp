package com.example.bahadir_eray_bootcampfinishproject.service

import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import com.example.bahadir_eray_bootcampfinishproject.service.CountriesAPI.Companion.API_HOST
import com.example.bahadir_eray_bootcampfinishproject.service.CountriesAPI.Companion.API_KEY
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class CountriesService {

    private val BASE_URL = "https://wft-geo-db.p.rapidapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountriesAPI::class.java)

    fun getCountries(): Single<List<CountriesModel>> {
        return api.getCountriesData(API_KEY, API_HOST)
    }
}