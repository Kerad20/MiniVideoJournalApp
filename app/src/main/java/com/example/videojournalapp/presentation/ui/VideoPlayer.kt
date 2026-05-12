package com.example.videojournalapp.presentation.ui

import android.media.browse.MediaBrowser
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView



@Composable
fun VideoPlayer(
    videoPath: String
) {

    val context = LocalContext.current

    val player = remember {

        ExoPlayer.Builder(context)
            .build()
            .apply {

                setMediaItem(
                    MediaItem.fromUri(
                        Uri.parse(videoPath)
                    )
                )

                prepare()
            }
    }

    DisposableEffect(Unit) {

        onDispose {
            player.release()
        }
    }

    AndroidView(

        factory = {

            PlayerView(it).apply {

                this.player = player

                useController = true
            }
        },

        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}
