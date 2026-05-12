package com.example.videojournalapp.core

import android.content.Context
import android.net.Uri
import java.io.File
import java.util.UUID

class VideoFileManager(
    private val context: Context
) {

    fun save(uri: Uri): String {

        val file = File(
            context.filesDir,
            "videos/${UUID.randomUUID()}.mp4"
        )

        file.parentFile?.mkdirs()

        context.contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }
}