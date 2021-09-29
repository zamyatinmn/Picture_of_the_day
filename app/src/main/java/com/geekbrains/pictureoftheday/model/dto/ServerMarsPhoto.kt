package com.geekbrains.pictureoftheday.model.dto

import com.google.gson.annotations.SerializedName


/**
 * Created by Maxim Zamyatin on 23.09.2021
 */


data class ServerMarsPhoto(
    @field:SerializedName("photos") val photos: ArrayList<ServerMarsData>,
)

data class ServerMarsData(
    @field:SerializedName("img_src") val imgSrc: String?,
    @field:SerializedName("earth_date") val earth_date: String?,
)