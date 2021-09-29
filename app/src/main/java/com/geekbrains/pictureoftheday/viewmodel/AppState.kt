package com.geekbrains.pictureoftheday.viewmodel

import com.geekbrains.pictureoftheday.model.dto.Element
import com.geekbrains.pictureoftheday.model.dto.ServerApodData
import com.geekbrains.pictureoftheday.model.dto.ServerMarsPhoto


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


sealed class AppState {
    data class Success(val serverResponseData: ServerApodData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

    data class SuccessEpic(val serverResponseData: ArrayList<Element>) : AppState()
    data class SuccessMars(val serverResponseData: ServerMarsPhoto) : AppState()
}