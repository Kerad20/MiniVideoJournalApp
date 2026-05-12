package com.example.videojournalapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videojournalapp.domain.model.FeedState
import com.example.videojournalapp.domain.usecase.ObserveEntriesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FeedViewModel(
    observe: ObserveEntriesUseCase
) : ViewModel() {

    val state = observe()
        .map { FeedState(items = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            FeedState()
        )
}