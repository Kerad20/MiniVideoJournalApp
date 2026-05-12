package com.example.videojournalapp.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.videojournalapp.domain.model.PermissionState
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

    val scope = CoroutineScope(Dispatchers.IO)

    private val _permissionState = MutableStateFlow(PermissionState())

    val permissionState = _permissionState.asStateFlow()

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
        _permissionState.update {
            it.copy(cameraGranted = camera, audioGranted = audio)
        }
    }

    fun insertVideoEntry(path: Uri, desc: String){
        scope.launch {
            insertEntryUseCase(path.toString(), desc)
        }

    }


}