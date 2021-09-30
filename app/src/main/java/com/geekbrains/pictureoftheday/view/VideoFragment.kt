package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.View
import com.geekbrains.pictureoftheday.databinding.FragmentVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


/**
 * Created by Maxim Zamyatin on 30.09.2021
 */


class VideoFragment : ViewBindingFragment<FragmentVideoBinding>(FragmentVideoBinding::inflate) {
    companion object {
        private const val ID_KEY = "id_key"

        fun newInstance(videoId: String): VideoFragment {
            val fragment = VideoFragment()
            val bundle = Bundle()
            bundle.putString(ID_KEY, videoId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ID_KEY)?.let { showVideo(it) }
    }

    private fun showVideo(id: String) {
        lifecycle.addObserver(ui.youtubePlayer)
        ui.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0f)
            }
        })
    }
}