package com.geekbrains.pictureoftheday.model

import com.geekbrains.pictureoftheday.BASE_URL
import com.geekbrains.pictureoftheday.model.dto.Element
import com.geekbrains.pictureoftheday.model.dto.ServerApodData
import com.geekbrains.pictureoftheday.model.dto.ServerMarsPhoto
import com.geekbrains.pictureoftheday.model.dto.ServerSolarFlareData
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


class RequestToAPI {

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(NasaApi::class.java)
    }

    fun getPictureOfTheDay(apiKey: String, date: String, podCallback: Callback<ServerApodData>) {
        api.getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }

    fun getEPIC(apiKey: String, epicCallback: Callback<ArrayList<Element>>) {
        api.getEarthPicture(apiKey).enqueue(epicCallback)
    }

    fun getMarsPictureByDate(
        earth_date: String,
        apiKey: String,
        marsCallbackByDate: Callback<ServerMarsPhoto>
    ) {
        api.getMarsImageByDate(earth_date, apiKey).enqueue(marsCallbackByDate)
    }

    fun getSolarFlare(
        apiKey: String,
        podCallback: Callback<List<ServerSolarFlareData>>,
        startDate: String = "2021-09-07"
    ) {
        api.getSolarFlare(apiKey, startDate).enqueue(podCallback)
    }
}