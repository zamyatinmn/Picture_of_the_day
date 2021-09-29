package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
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
                    TransitionManager.beginDelayedTransition(ui.linear, Slide(Gravity.END))
                    view.load(url) {
                        placeholder(R.drawable.progress_animation)
                        error(R.drawable.ic_no_photo_vector)
                    }
                    setOnClickAnimation(view)
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

    private fun setOnClickAnimation(view: ImageView) {
        var isBig = false
        view.setOnClickListener {
            if (isBig) {
                TransitionManager.beginDelayedTransition(ui.linear)
                view.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            } else {
                TransitionManager.beginDelayedTransition(ui.linear)
                val params: ViewGroup.LayoutParams = view.layoutParams
                params.width = ui.root.width
                params.height = ui.root.height
                view.layoutParams = params
                ui.scroll.requestChildFocus(view, view)
            }
            isBig = !isBig
        }
    }
}