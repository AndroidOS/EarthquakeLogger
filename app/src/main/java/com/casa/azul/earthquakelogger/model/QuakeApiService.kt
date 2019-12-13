package com.casa.azul.earthquakelogger.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuakeApiService {

    private val BASE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(QuakeApi::class.java)

    fun getMovies(): Single<RootObject> {
        return api.getQuakes()
    }

}