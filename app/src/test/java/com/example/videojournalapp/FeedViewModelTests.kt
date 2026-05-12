package com.example.videojournalapp

import com.example.Journal_entry
import com.example.videojournalapp.domain.model.JournalEntry
import com.example.videojournalapp.domain.usecase.ObserveEntriesUseCase
import com.example.videojournalapp.presentation.viewmodels.FeedViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description



@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTests {
    private val observeUseCase =
        mockk<ObserveEntriesUseCase>()

    private lateinit var viewModel: FeedViewModel

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Test
    fun `initial empty state`() = runTest {

        every {
            observeUseCase()
        } returns flowOf(
            emptyList()
        )

        viewModel = FeedViewModel(
            observeUseCase
        )

        assertTrue(
            viewModel.state.value.items.isEmpty()
        )
    }

    @Test
    fun `load entries`() = runTest {

        val entries = listOf(
            JournalEntry(1, "video1", "desc1", 100L)
        )

        every {
            observeUseCase()
        } returns flowOf(entries)

        val viewModel = FeedViewModel(observeUseCase)

        assertEquals(
            entries,
            viewModel.state.value.items
        )
    }

    @Test
    fun `update state when database changes`() = runTest {

        val flow =
            MutableSharedFlow<List<JournalEntry>>()

        every {
            observeUseCase()
        } returns flow

        viewModel = FeedViewModel(
            observeUseCase
        )

        val first =
            listOf(
                JournalEntry(
                    1,
                    "video1",
                    "desc1",
                    100
                )
            )

        val second =
            listOf(
                JournalEntry(
                    2,
                    "video2",
                    "desc2",
                    200
                )
            )

        flow.emit(first)

        advanceUntilIdle()

        assertEquals(
            first,
            viewModel.state.value.items
        )

        flow.emit(second)

        advanceUntilIdle()

        assertEquals(
            second,
            viewModel.state.value.items
        )
    }
}