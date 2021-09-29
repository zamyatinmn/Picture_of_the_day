package com.geekbrains.pictureoftheday.model.dto


/**
 * Created by Maxim Zamyatin on 23.09.2021
 */


data class ServerSolarFlareData(
    val flrID: String,
    val beginTime: String,
    val peakTime: String,
    val endTime: Any? = null,
    val classType: String,
    val sourceLocation: String,
    val activeRegionNum: Long,
    val link: String
)