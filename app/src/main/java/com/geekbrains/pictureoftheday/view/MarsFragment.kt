package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.FragmentMarsBinding
import com.geekbrains.pictureoftheday.viewmodel.AppState
import com.geekbrains.pictureoftheday.viewmodel.MarsViewModel
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Maxim Zamyatin on 12.09.2021
 */


class MarsFragment : ViewBindingFragment<FragmentMarsBinding>(FragmentMarsBinding::inflate) {
    companion object {
        fun newInstance() = MarsFragment()
    }

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date().time)
        viewModel.serverRequest(currentDate)
    }

    private fun renderData(data: AppState) {

        when (data) {
            is AppState.Error -> {
                Toast.makeText(context, "MarsData.Error", Toast.LENGTH_LONG).show()
                ui.image.setImageResource(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                ui.image.load(R.drawable.progress_animation) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is AppState.SuccessMars -> {
                if (data.serverResponseData.photos.size == 0) {
                    ui.image.load(R.drawable.ic_no_photo_vector) {
                        size(4000)
                    }
                } else {
                    ui.image.visibility = View.GONE
                    for (element in data.serverResponseData.photos) {
                        val view = ImageView(requireContext())
                        view.load(element.imgSrc) {
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
            }
            else -> {
                //do nothing
            }
        }
    }
}