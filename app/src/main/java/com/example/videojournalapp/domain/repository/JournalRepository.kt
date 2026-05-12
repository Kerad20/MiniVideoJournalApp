package com.example.videojournalapp.domain.repository

import com.example.videojournalapp.domain.model.JournalEntry
import kotlinx.coroutines.flow.Flow

interface JournalRepository {
    fun observeEntries(): Flow<List<JournalEntry>>

    suspend fun insert(
        videoPath: String,
        description: String?
    )

    suspend fun delete(id: Long)
}