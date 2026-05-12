package com.example.videojournalapp.domain.model

data class RecorderState(
    val description: String = "",
    val hasPermissions: Boolean = false,
    val isCameraRecording: Boolean = false
)
