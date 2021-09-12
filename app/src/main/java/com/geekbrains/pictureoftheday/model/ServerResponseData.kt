package com.geekbrains.pictureoftheday.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


data class ServerResponseData(
    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("media_type") val mediaType: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("hdurl") val hdurl: String?
)