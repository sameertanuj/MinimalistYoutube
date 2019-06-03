package com.samoana.minimalistyoutube.ui.main


import android.os.Bundle

import com.samoana.minimalistyoutube.R
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_video_player.*
import android.app.PictureInPictureParams
import android.util.Rational


class VideoPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_video_player)
        val videoId = intent.getStringExtra("video_id")
        if(videoId != null) {
            video_title.text = intent.getStringExtra("title")
            description.text = intent.getStringExtra("description")
            initYouTubePlayerView(videoId)
        }
    }

    private fun initYouTubePlayerView(videoId: String) {
        lifecycle.addObserver(youtube_player_view)
        initPictureInPicture(youtube_player_view)
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    private fun initPictureInPicture(youTubePlayerView: YouTubePlayerView) {
        val pictureInPictureIcon = ImageView(this)
        pictureInPictureIcon.setImageDrawable(ContextCompat.getDrawable(this, com.samoana.minimalistyoutube.R.drawable.ic_picture_in_picture_24dp))
        pictureInPictureIcon.setOnClickListener { view ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val supportsPIP = packageManager.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
                if (supportsPIP) {
                    val aspectRatio = Rational(pictureInPictureIcon.width, pictureInPictureIcon.height)
                    val params = PictureInPictureParams.Builder()
                        .setAspectRatio(aspectRatio)
                        .build()
                    enterPictureInPictureMode(params)
                }
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Can't enter picture in picture mode")
                    .setMessage("In order to enter picture in picture mode you need a SDK version >= N.")
                    .show()
            }
        }

        youTubePlayerView.getPlayerUiController().addView(pictureInPictureIcon)
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)

        if (isInPictureInPictureMode) {
            youtube_player_view.enterFullScreen()
            youtube_player_view.getPlayerUiController().showUi(false)
        } else {
            youtube_player_view.exitFullScreen()
            youtube_player_view.getPlayerUiController().showUi(true)
        }
    }

}
