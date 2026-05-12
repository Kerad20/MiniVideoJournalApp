package com.example.videojournalapp.di

import com.example.Database
import com.example.videojournalapp.data.DatabaseFactory
import com.example.videojournalapp.data.JournalLocalDataSource
import com.example.videojournalapp.data.JournalRepositoryImpl
import com.example.videojournalapp.domain.repository.JournalRepository
import com.example.videojournalapp.domain.usecase.InsertEntryUseCase
import com.example.videojournalapp.domain.usecase.ObserveEntriesUseCase
import com.example.videojournalapp.presentation.viewmodels.FeedViewModel
import com.example.videojournalapp.presentation.viewmodels.RecorderViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val appModule = module {
    single { DatabaseFactory(get()).create() }

    single { get<Database>().journalEntryQueries }

    single { JournalLocalDataSource(get()) }

    single<JournalRepository> {
        JournalRepositoryImpl(get())
    }

    single { InsertEntryUseCase(get()) }

    single { ObserveEntriesUseCase(get()) }

    factory { FeedViewModel(get()) }

    factory { RecorderViewModel(get()) }
}