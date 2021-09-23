package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.geekbrains.pictureoftheday.*
import com.geekbrains.pictureoftheday.databinding.FragmentEpicBinding
import com.geekbrains.pictureoftheday.viewmodel.AppState
import com.geekbrains.pictureoftheday.viewmodel.EpicViewModel


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


class EpicFragment : ViewBindingFragment<FragmentEpicBinding>(FragmentEpicBinding::inflate) {
    companion object {
        fun newInstance() = EpicFragment()
    }

    private val viewModel: EpicViewModel by lazy {
        ViewModelProvider(this).get(EpicViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.serverRequest()
    }

    private fun renderData(data: AppState) {

        when (data) {
            is AppState.Error -> {
                Toast.makeText(context, "EPICData.Error", Toast.LENGTH_LONG).show()
                ui.image.setImageResource(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                ui.image.load(R.drawable.progress_animation) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is AppState.SuccessEpic -> {
                ui.image.visibility = View.GONE
                for (element in data.serverResponseData) {
                    val view = ImageView(requireContext())
                    val dateTime = element.date.split(" ")
                    val date = dateTime[0].replace("-", "/")

                    val url = "$BASE_URL$EPIC_PICTURE_ENDPOINT$date" +
                            "/png/${element.image}.png?$API_KEY=${BuildConfig.NASA_API_KEY}"
                    view.load(url) {
                        placeholder(R.drawable.progress_animation)
                        error(R.drawable.ic_no_photo_vector)
                    }
                    ui.linear.gravity = Gravity.CENTER_HORIZONTAL
                    ui.linear.addView(
                        view,
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    )
                }
            }
            else -> {
                //do nothing
            }
        }
    }
}