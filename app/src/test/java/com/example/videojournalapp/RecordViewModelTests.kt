package com.example.videojournalapp

import android.net.Uri
import com.example.videojournalapp.domain.usecase.InsertEntryUseCase
import com.example.videojournalapp.presentation.viewmodels.RecorderViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecordViewModelTests {

    private val insertUseCase =
        mockk<InsertEntryUseCase>()

    private lateinit var viewModel: RecorderViewModel

    @Before
    fun setup() {

        coEvery {
            insertUseCase(any(), any())
        } returns Unit

        viewModel = RecorderViewModel(
            insertUseCase
        )
    }

    @Test
    fun `initial state`() {

        val state = viewModel.state.value

        assertEquals("", state.description)
        assertFalse(state.isCameraRecording)
        assertFalse(state.hasPermissions)
    }

    @Test
    fun `update description`() {

        viewModel.onDescriptionChange(
            "new video"
        )

        assertEquals(
            "new video",
            viewModel.state.value.description
        )
    }

    @Test
    fun `start recording`() {

        viewModel.startCamera(
            true
        )

        assertTrue(
            viewModel.state.value.isCameraRecording
        )
    }

    @Test
    fun `update permission state`() {

        viewModel.updatePermissionState(
            camera = true,
            audio = true
        )

        assertTrue(
            viewModel.state.value.hasPermissions
        )
    }

    @Test
    fun `insert video entry`(): Unit = runTest {

        val uri = mockk<Uri>()

        every {
            uri.toString()
        } returns "content://test"

        viewModel.insertVideoEntry(
            uri,
            "desc"
        )

        advanceUntilIdle()

        coVerify(exactly = 1) {

            insertUseCase(
                "content://test",
                "desc"
            )
        }
    }

}