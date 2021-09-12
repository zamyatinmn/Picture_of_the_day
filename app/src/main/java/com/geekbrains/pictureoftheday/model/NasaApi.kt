package com.geekbrains.pictureoftheday.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */

interface NasaApi {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String, @Query("date") date: String
    ): Call<ServerResponseData>
}