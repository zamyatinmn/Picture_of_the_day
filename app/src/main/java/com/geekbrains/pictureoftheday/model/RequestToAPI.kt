package com.geekbrains.pictureoftheday.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


class RequestToAPI {
    private val baseUrl = "https://api.nasa.gov/"

    fun getAPI(): NasaApi {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(NasaApi::class.java)
    }
}