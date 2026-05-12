package com.example.videojournalapp.domain.model

data class FeedState(
    val items: List<JournalEntry> = emptyList(),
    val isLoading: Boolean = false
)
