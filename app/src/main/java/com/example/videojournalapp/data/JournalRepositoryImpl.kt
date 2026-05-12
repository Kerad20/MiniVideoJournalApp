package com.example.videojournalapp.data

import com.example.videojournalapp.domain.model.JournalEntry
import com.example.videojournalapp.domain.repository.JournalRepository
import kotlinx.coroutines.flow.map

class JournalRepositoryImpl(
    private val local: JournalLocalDataSource
) : JournalRepository {

    override fun observeEntries() =
        local.observe()
            .map { list ->
                list.map {
                    JournalEntry(
                        id = it.id,
                        videoPath = it.video_path,
                        description = it.description ?: "",
                        createdAt = it.created_at
                    )
                }
            }

    override suspend fun insert(
        videoPath: String,
        description: String?
    ) {
        local.insert(videoPath, description)
    }

    override suspend fun delete(id: Long) {
        local.delete(id)
    }
}