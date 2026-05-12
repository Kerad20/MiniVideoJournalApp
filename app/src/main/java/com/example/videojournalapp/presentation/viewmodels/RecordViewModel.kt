package com.example.videojournalapp.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videojournalapp.domain.model.RecorderState
import com.example.videojournalapp.domain.usecase.InsertEntryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecorderViewModel(
   private val insertEntryUseCase: InsertEntryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RecorderState())
    val state = _state.asStateFlow()

    fun onDescriptionChange(value: String) {
        _state.update {
            it.copy(description = value)
        }
    }

    fun startCamera(value: Boolean) {
        _state.update {
            it.copy(isCameraRecording = value)
        }
    }

    fun updatePermissionState(camera: Boolean, audio: Boolean){
        _state.update {
            it.copy(hasPermissions = camera && audio)
        }
    }

    fun insertVideoEntry(path: Uri, desc: String){
        viewModelScope.launch {
            insertEntryUseCase(path.toString(), desc)
        }

    }


}