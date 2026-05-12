package com.example.videojournalapp.domain.model

data class PermissionState(
    val cameraGranted: Boolean = false,
    val audioGranted: Boolean = false
)
