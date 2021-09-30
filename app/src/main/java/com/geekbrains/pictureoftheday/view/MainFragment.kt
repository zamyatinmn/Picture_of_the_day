package com.geekbrains.pictureoftheday.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.geekbrains.pictureoftheday.DAY_BEFORE_YESTERDAY_MODE
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.TODAY_MODE
import com.geekbrains.pictureoftheday.YESTERDAY_MODE
import com.geekbrains.pictureoftheday.databinding.FragmentPictureBinding
import com.geekbrains.pictureoftheday.viewmodel.AppState
import com.geekbrains.pictureoftheday.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */

class MainFragment : ViewBindingFragment<FragmentPictureBinding>(FragmentPictureBinding::inflate) {

    companion object {
        fun newInstance(mode: String = TODAY_MODE): Fragment {
            val bundle = Bundle()
            bundle.putString("mode", mode)
            return MainFragment().apply { arguments = bundle }
        }
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date().time)

        when (arguments?.getString("mode")) {
            TODAY_MODE -> viewModel.sendServerRequest(currentDate)
            YESTERDAY_MODE -> {
                val date = Calendar.getInstance()
                date.add(Calendar.DATE, -1)
                viewModel.sendServerRequest(sdf.format(date.timeInMillis))
            }
            DAY_BEFORE_YESTERDAY_MODE -> {
                val date = Calendar.getInstance()
                date.add(Calendar.DATE, -2)
                viewModel.sendServerRequest(sdf.format(date.timeInMillis))
            }
        }

        ui.wikiContainer.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${ui.wiki.text}")
            }
            startActivity(intent)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(ui.bot.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                Toast.makeText(context, "PODData.Error", Toast.LENGTH_LONG).show()
                ui.picture.setImageResource(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                ui.picture.load(R.drawable.progress_animation) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is AppState.Success -> {
                if (data.serverResponseData.mediaType == "video") {
                    ui.picture.load(R.drawable.youtube_logo)
                    ui.picture.setOnClickListener {
                        val id = data.serverResponseData.url?.substringAfterLast("/")
                            ?.substringBefore("?")
                        childFragmentManager.beginTransaction()
                            .add(ui.coord.id, VideoFragment.newInstance(id!!))
                            .commit()
                    }
                } else {
                    ui.picture.load(data.serverResponseData.url) {
                        placeholder(R.drawable.progress_animation)
                        error(R.drawable.ic_no_photo_vector)
                        size(4000)
                    }
                }
                data.serverResponseData.title?.let {
                    ui.titlePhoto.text = it
                }
                data.serverResponseData.explanation.let {
                    ui.bot.desc.text = it
                }
            }
        }
    }
}