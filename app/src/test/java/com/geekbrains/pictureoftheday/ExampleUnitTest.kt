package com.geekbrains.pictureoftheday

import androidx.lifecycle.MutableLiveData
import com.geekbrains.pictureoftheday.model.RequestToAPI
import com.geekbrains.pictureoftheday.viewmodel.AppState
import com.geekbrains.pictureoftheday.viewmodel.MainViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var request: RequestToAPI

    @Mock
    lateinit var liveData: MutableLiveData<AppState>

    private val date = "02.02.2022"

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(liveData, request)
    }

    @Test
    fun isSameLiveData() {
        assertTrue(mainViewModel.getLiveData() == liveData)
    }

    @Test
    fun isOnlyOneCallRequest() {
        mainViewModel.sendServerRequest(date)
        Mockito.verify(request, Mockito.times(1))
            .getPictureOfTheDay(eq(BuildConfig.NASA_API_KEY), eq(date), any())
    }
}