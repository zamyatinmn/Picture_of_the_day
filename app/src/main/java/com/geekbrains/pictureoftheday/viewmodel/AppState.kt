package com.geekbrains.pictureoftheday.viewmodel

import com.geekbrains.pictureoftheday.model.Element
import com.geekbrains.pictureoftheday.model.ServerApodData


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


sealed class AppState {
    data class Success(val serverResponseData: ServerApodData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

    data class SuccessEpic(val serverResponseData: ArrayList<Element>) : AppState()
}