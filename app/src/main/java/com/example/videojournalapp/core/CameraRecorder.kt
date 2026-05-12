package com.example.videojournalapp.core

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.annotation.RequiresPermission
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.core.content.ContextCompat
import java.io.File

class CameraRecorder(
    private val context: Context,
    private val controller: LifecycleCameraController
) {

    private var recording: Recording? = null

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun startRecording(
        onResult: (Uri) -> Unit
    ) {

//        val file = File(
//            context.filesDir,
//            "video_${System.currentTimeMillis()}.mp4"
//        )

//        val outputOptions =
//            FileOutputOptions.Builder(file).build()

        val outputOptions = MediaStoreOutputOptions.Builder(
            context.contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
            .setContentValues(ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "video_${System.currentTimeMillis()}")
                put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
                put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/JournalApp")
            })
            .build()

        val audioConfig = AudioConfig.create(true)

        recording = controller.startRecording(
            outputOptions,
            audioConfig,
            ContextCompat.getMainExecutor(context)
        ) { event ->

            if (event is VideoRecordEvent.Finalize) {

                onResult(event.outputResults.outputUri)
            }
        }
    }

    fun stopRecording() {
        recording?.stop()
        recording = null
    }
}