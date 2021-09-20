package com.geekbrains.pictureoftheday.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.pictureoftheday.BuildConfig
import com.geekbrains.pictureoftheday.model.Element
import com.geekbrains.pictureoftheday.model.RequestToAPI
import com.geekbrains.pictureoftheday.viewmodel.AppState
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
            liveData.postValue(AppState.Error(Throwable("Empty api key")))
        } else {
            request.getAPI().getEarthPicture(apiKey).enqueue(
                object : Callback<ArrayList<Element>> {
                    override fun onResponse(
                        call: Call<ArrayList<Element>>,
                        response: Response<ArrayList<Element>>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveData.postValue(AppState.SuccessEpic(response.body()!!))
                        } else {
                            liveData.postValue(AppState.Error(Throwable("Something wrong..")))
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Element>>, t: Throwable) {
                        liveData.postValue(AppState.Error(t))
                    }
                }
            )
        }
    }
}