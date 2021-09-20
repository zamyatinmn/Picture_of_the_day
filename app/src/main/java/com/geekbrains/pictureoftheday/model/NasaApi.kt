package com.geekbrains.pictureoftheday.model

import com.geekbrains.pictureoftheday.API_DATE
import com.geekbrains.pictureoftheday.API_KEY
import com.geekbrains.pictureoftheday.APOD_ENDPOINT
import com.geekbrains.pictureoftheday.EPIC_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */

interface NasaApi {
    @GET(APOD_ENDPOINT)
    fun getPictureOfTheDay(
        @Query(API_KEY) apiKey: String, @Query(API_DATE) date: String
    ): Call<ServerApodData>

    @GET(EPIC_ENDPOINT)
    fun getEarthPicture(
        @Query(API_KEY) apiKey: String
    ): Call<ArrayList<Element>>
}