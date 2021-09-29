package com.geekbrains.pictureoftheday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.pictureoftheday.BuildConfig
import com.geekbrains.pictureoftheday.EMPTY_API_KEY
import com.geekbrains.pictureoftheday.UNKNOWN_ERROR
import com.geekbrains.pictureoftheday.model.RequestToAPI
import com.geekbrains.pictureoftheday.model.dto.Element
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


class EpicViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val request: RequestToAPI = RequestToAPI()
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun serverRequest() {
        liveData.postValue(AppState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.postValue(AppState.Error(Throwable(EMPTY_API_KEY)))
        } else {
            request.getEPIC(apiKey, epicCallback)
        }
    }

    private val epicCallback = object : Callback<ArrayList<Element>> {
        override fun onResponse(
            call: Call<ArrayList<Element>>,
            response: Response<ArrayList<Element>>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(AppState.SuccessEpic(response.body()!!))
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveData.postValue(AppState.Error(Throwable(UNKNOWN_ERROR)))
                } else {
                    liveData.postValue(AppState.Error(Throwable(message)))
                }
            }
        }

        override fun onFailure(call: Call<ArrayList<Element>>, t: Throwable) {
            liveData.postValue(AppState.Error(t))
        }
    }
}