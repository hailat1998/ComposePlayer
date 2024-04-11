package com.hd.composevideoPlayer.videoPlayer

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun Player(context: Context, url: String, selected: Boolean){
    val videoPlayer = remember{
    ExoPlayer.Builder(context)
        .build().apply {
            val mediaSource = ProgressiveMediaSource
                .Factory(
                    DefaultDataSourceFactory(context, null)
                )
                .createMediaSource(MediaItem.fromUri(Uri.parse("asset:///${url}")))

            this.setMediaSource(mediaSource, true)
            this.prepare()
        }
}
    videoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_OFF
    AndroidView({
        PlayerView(it).apply {
            useController = true
            player = videoPlayer
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        }
    }
    )
    videoPlayer.playWhenReady = true
    DisposableEffect(key1 = true) {
        onDispose {
            videoPlayer.release()
        }
    }
}






