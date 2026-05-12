package com.example.videojournalapp.domain.usecase

import com.example.videojournalapp.domain.repository.JournalRepository

class InsertEntryUseCase(
    private val repo: JournalRepository
) {
    suspend operator fun invoke(path: String, desc: String?) {
        repo.insert(path, desc)
    }
}