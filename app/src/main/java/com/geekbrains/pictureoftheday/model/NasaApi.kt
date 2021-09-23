package com.geekbrains.pictureoftheday.model

import com.geekbrains.pictureoftheday.*
import com.geekbrains.pictureoftheday.model.dto.*
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

    @GET(MARS_ENDPOINT)
    fun getMarsImageByDate(
        @Query("earth_date") earth_date: String,
        @Query("api_key") apiKey: String,
    ): Call<ServerMarsPhoto>


    @GET(SOLAR_FLARE_ENDPOINT)
    fun getSolarFlare(
        @Query("api_key") apiKey: String,
        @Query("startDate") startDate: String,
    ): Call<List<ServerSolarFlareData>>

    @GET(EARTH_MAP_ENDPOINT)
    fun getLandscapeImageFromSputnik(
        @Query("lon") lon: Float,
        @Query("lat") lat: Float,
        @Query("date") dateString: String,
        @Query("dim") dim: Float,
        @Query("api_key") apiKey: String
    ): Call<ServerSatelliteData>
}