package com.geekbrains.pictureoftheday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.pictureoftheday.BuildConfig
import com.geekbrains.pictureoftheday.model.RequestToAPI
import com.geekbrains.pictureoftheday.model.ServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val request: RequestToAPI = RequestToAPI()
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun sendServerRequest(date: String) {
        liveData.postValue(AppState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.postValue(AppState.Error(Throwable("Empty api key")))
        } else {
            request.getAPI().getPictureOfTheDay(apiKey, date).enqueue(
                object : Callback<ServerResponseData> {
                    override fun onResponse(
                        call: Call<ServerResponseData>,
                        response: Response<ServerResponseData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveData.postValue(AppState.Success(response.body()!!))
                        } else {
                            liveData.postValue(AppState.Error(Throwable("Something wrong..")))
                        }
                    }

                    override fun onFailure(call: Call<ServerResponseData>, t: Throwable) {
                        liveData.postValue(AppState.Error(t))
                    }
                }
            )
        }
    }
}