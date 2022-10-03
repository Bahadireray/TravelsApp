package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.CountryDatabase
import com.example.bahadir_eray_bootcampfinishproject.service.CountryAPIService
import com.example.bahadir_eray_bootcampfinishproject.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : BaseViewModel(application) {
    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    val countriesModel = MutableLiveData<List<CountryModel>>()
    private var refreshTime = 0.1 * 60 * 1000 * 1000 * 1000L


    fun refreshDataTop() {
        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLiteTop()
        } else {
            getDataFromAPITop()
        }
    }

    fun getDataFromSQLiteTop() {
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountriesTop(countries)
            Toast.makeText(getApplication(), "Countries From SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataFromAPITop() {
        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>() {
                    override fun onSuccess(t: List<CountryModel>) {
                        storeSQLiteTop(t)
                        Toast.makeText(getApplication(), "Countries From API", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onError(e: Throwable) {
                        Log.v("Search", "Error " + e.localizedMessage)
                    }
                })
        )
    }

    private fun showCountriesTop(countriesList: List<CountryModel>) {
        countriesModel.value = countriesList
    }

    private fun storeSQLiteTop(list: List<CountryModel>) {

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())  //List-> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountriesTop(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}