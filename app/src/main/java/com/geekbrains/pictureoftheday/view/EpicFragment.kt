package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.geekbrains.pictureoftheday.*
import com.geekbrains.pictureoftheday.databinding.FragmentEpicBinding
import com.geekbrains.pictureoftheday.viewmodel.AppState


/**
 * Created by Maxim Zamyatin on 20.09.2021
 */


class EpicFragment : Fragment() {
    companion object {
        fun newInstance() = EpicFragment()
    }

    private val viewModel: EpicViewModel by lazy {
        ViewModelProvider(this).get(EpicViewModel::class.java)
    }

    private var _ui: FragmentEpicBinding? = null
    private val ui: FragmentEpicBinding
        get() = _ui!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = FragmentEpicBinding.inflate(inflater)
        return ui.root
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
                    val date = dateTime[0].split("-")
                    val url = "$BASE_URL$EPIC_PICTURE_ENDPOINT${date[0]}/${date[1]}/${date[2]}" +
                            "/png/${element.image}.png?$API_KEY=${BuildConfig.NASA_API_KEY}"
                    view.load(url) {
                        placeholder(R.drawable.progress_animation)
                        error(R.drawable.ic_no_photo_vector)
                    }
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

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}