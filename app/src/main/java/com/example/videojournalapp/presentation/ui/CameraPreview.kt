package com.example.videojournalapp.presentation.ui

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun CameraPreview(
    controller: LifecycleCameraController
) {

    val context = LocalContext.current

    AndroidView(
        factory = {
            PreviewView(context).apply {
                this.controller = controller
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun createCameraController(context: Context): LifecycleCameraController {

    return LifecycleCameraController(context).apply {

        setEnabledUseCases(
            CameraController.VIDEO_CAPTURE
        )

    }
}