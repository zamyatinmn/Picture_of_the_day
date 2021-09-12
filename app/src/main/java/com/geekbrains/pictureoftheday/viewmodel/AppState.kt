package com.geekbrains.pictureoftheday.viewmodel

import com.geekbrains.pictureoftheday.model.ServerResponseData


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */


sealed class AppState {
    data class Success(val serverResponseData: ServerResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}