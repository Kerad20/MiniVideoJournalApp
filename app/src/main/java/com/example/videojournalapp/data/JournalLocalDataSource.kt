package com.example.videojournalapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.JournalEntryQueries
import com.example.Journal_entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class JournalLocalDataSource(
    private val queries: JournalEntryQueries
) {

    fun observe(): Flow<List<Journal_entry>> =
        queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

    suspend fun insert(
        path: String,
        desc: String?
    ) {
        queries.insert(
            video_path = path,
            description = desc ?: "",
            created_at = System.currentTimeMillis()
        )
    }

    suspend fun delete(id: Long) {
        queries.deleteById(id)
    }
}