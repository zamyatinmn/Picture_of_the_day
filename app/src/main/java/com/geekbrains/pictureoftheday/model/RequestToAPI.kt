package com.geekbrains.pictureoftheday.model

import com.geekbrains.pictureoftheday.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


class RequestToAPI {

    fun getAPI(): NasaApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(NasaApi::class.java)
    }
}