package com.example.videojournalapp.domain.usecase

import com.example.videojournalapp.domain.repository.JournalRepository

class ObserveEntriesUseCase(
    private val repo: JournalRepository
) {
    operator fun invoke() = repo.observeEntries()
}