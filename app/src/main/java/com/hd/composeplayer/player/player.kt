package com.hd.composeplayer.player

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource

@OptIn(UnstableApi::class) @Composable
fun Player(context: Context, url: String, selected: Boolean){
val player = remember{
    ExoPlayer.Builder(context)
        .build().apply {
            val mediaSource = ProgressiveMediaSource
                .Factory(
                    DefaultDataSourceFactory(context, "composePlayer")
                )
                .createMediaSource(MediaItem.fromUri(Uri.parse("asset:///${url}")))

            this.setMediaSource(mediaSource, true)
            this.prepare()
        }
}

}


fun initializePlayer(context: Context) {
    ExoPlayer.Builder(context)
        .build()
}


@Composable
private fun hideSystemUi() {
    val systemUiController = SystemUiController(window)
    systemUiController.hideSystemBars()
}

