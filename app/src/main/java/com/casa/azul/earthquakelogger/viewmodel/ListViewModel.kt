package com.casa.azul.earthquakelogger.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.casa.azul.earthquakelogger.model.Feature
import com.casa.azul.earthquakelogger.model.QuakeApiService
import com.casa.azul.earthquakelogger.model.RootObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "ListViewModel"

class ListViewModel(application: Application) : BaseViewModel(application) {

    private val movieService = QuakeApiService()
    private val disposable = CompositeDisposable()

    val quakes = MutableLiveData<List<Feature>>()
    val loading = MutableLiveData<Boolean>()
    val error_loading = MutableLiveData<Boolean>()
    val menu_email = MutableLiveData<Boolean>()



    fun refresh() {
        getUTCDate()
        getPreviousDayDate()
        loading.value = true
        error_loading.value = false
        fetchFromRemote()
    }

    fun getDetailQuake(i: Int): Feature {
        return quakes.value!![i]
    }


    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            movieService.getQuakes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RootObject>() {
                    override fun onSuccess(quakeList: RootObject) {
                        quakes.value = quakeList.features
                        error_loading.value = false
                        loading.value = false
                        Log.d(TAG, "RxJava  ${quakes.value?.size}")
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        e.printStackTrace()
                        error_loading.value = true
                        loading.value = false
                        Log.d(TAG, "RxJava Error ${e.printStackTrace()}")
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun getUTCDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val utcTime = sdf.format(Date())

        Log.d(TAG, "$utcTime")
        return utcTime

    }

    private fun getPreviousDayDate(): String {

        val dayMilliseconds = 24 * 60 * 60 * 1000
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        var timeInMili = calendar.timeInMillis

        timeInMili -= dayMilliseconds

        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val utcTime = sdf.format(timeInMili)
        Log.d(TAG, "$utcTime")
        return ""
    }


}

