package com.geekbrains.pictureoftheday.model.dto

import com.google.gson.annotations.SerializedName


/**
 * Created by Maxim Zamyatin on 23.09.2021
 */


data class ServerSatelliteData(
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("date") val date: String?,
)