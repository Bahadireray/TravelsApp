package com.example.bahadir_eray_bootcampfinishproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bahadir_eray_bootcampfinishproject.data.model.countries.CountriesModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.CountriesDataBase
import com.example.bahadir_eray_bootcampfinishproject.service.CountriesService
import com.example.bahadir_eray_bootcampfinishproject.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : BaseViewModel(application) {
    val countriesModel = MutableLiveData<List<CountriesModel>>()
    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L


    override fun onCleared() {
    }

    fun refreshData() {
        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }
    }

    fun getDataFromSQLite() {
        launch {
            val countries = CountriesDataBase(getApplication()).countriesDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(), "Countries From SQLite", Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromAPI() {
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CountriesModel>>() {
                    override fun onSuccess(t: List<CountriesModel>) {
                        storeSQLite(t)
                        Toast.makeText(getApplication(), "Countries From API", Toast.LENGTH_LONG)
                            .show()
                    }

                    override fun onError(e: Throwable) {
                        Log.v("Search", "Error " + e.localizedMessage)
                    }
                })
        )
    }

    private fun showCountries(countriesList: List<CountriesModel>) {
        countriesModel.value = countriesList
    }

    private fun storeSQLite(list: List<CountriesModel>) {

        launch {
            val dao = CountriesDataBase(getApplication()).countriesDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())  //List-> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountries(list)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }

}