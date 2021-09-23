package com.geekbrains.pictureoftheday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.pictureoftheday.BuildConfig
import com.geekbrains.pictureoftheday.EMPTY_API_KEY
import com.geekbrains.pictureoftheday.UNKNOWN_ERROR
import com.geekbrains.pictureoftheday.model.RequestToAPI
import com.geekbrains.pictureoftheday.model.dto.ServerMarsPhoto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Maxim Zamyatin on 23.09.2021
 */


class MarsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val request: RequestToAPI = RequestToAPI()
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun serverRequest(earth_date: String) {
        liveData.postValue(AppState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.postValue(AppState.Error(Throwable(EMPTY_API_KEY)))
        } else {
            request.getMarsPictureByDate(earth_date, apiKey, marsCallback)
        }
    }

    private val marsCallback = object : Callback<ServerMarsPhoto> {

        override fun onResponse(
            call: Call<ServerMarsPhoto>,
            response: Response<ServerMarsPhoto>,
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(AppState.SuccessMars(response.body()!!))
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveData.postValue(AppState.Error(Throwable(UNKNOWN_ERROR)))
                } else {
                    liveData.postValue(AppState.Error(Throwable(message)))
                }
            }
        }

        override fun onFailure(call: Call<ServerMarsPhoto>, t: Throwable) {
            liveData.postValue(AppState.Error(t))
        }
    }
}