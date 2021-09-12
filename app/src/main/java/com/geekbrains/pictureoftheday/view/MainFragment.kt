package com.geekbrains.pictureoftheday.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.geekbrains.pictureoftheday.R
import com.geekbrains.pictureoftheday.databinding.FragmentPictureBinding
import com.geekbrains.pictureoftheday.viewmodel.AppState
import com.geekbrains.pictureoftheday.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Maxim Zamyatin on 11.09.2021
 */

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _ui: FragmentPictureBinding? = null
    private val ui: FragmentPictureBinding
        get() = _ui!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = FragmentPictureBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date().time)

        viewModel.sendServerRequest(currentDate)

        ui.wikiContainer.setEndIconOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${ui.wiki.text}")
            }
            startActivity(intent)
        }

        ui.yesterday.setOnClickListener {
            val date = Calendar.getInstance()
            date.add(Calendar.DATE, -1)
//            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            viewModel.sendServerRequest(sdf.format(date.timeInMillis))
        }

        ui.today.setOnClickListener {
            viewModel.sendServerRequest(currentDate)
        }

        ui.dayBeforeYesterday.setOnClickListener {
            val date = Calendar.getInstance()
            date.add(Calendar.DATE, -2)
            viewModel.sendServerRequest(sdf.format(date.timeInMillis))
        }
        bottomSheetBehavior = BottomSheetBehavior.from(ui.bot.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                ui.loading.visibility = View.GONE
                Toast.makeText(context, "PODData.Error", Toast.LENGTH_LONG).show()
                ui.picture.setImageResource(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                ui.loading.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                ui.loading.visibility = View.GONE
                ui.picture.load(data.serverResponseData.url) {
                    error(R.drawable.ic_no_photo_vector)
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

    override fun onDestroy() {
        super.onDestroy()
        _ui = null
    }
}