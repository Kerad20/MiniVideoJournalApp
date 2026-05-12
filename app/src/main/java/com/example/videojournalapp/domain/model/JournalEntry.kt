package com.example.videojournalapp.domain.model

data class JournalEntry(
    val id: Long,
    val videoPath: String,
    val description: String,
    val createdAt: Long
)
