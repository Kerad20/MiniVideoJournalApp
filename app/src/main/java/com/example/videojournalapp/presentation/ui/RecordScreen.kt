package com.example.videojournalapp.presentation.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.videojournalapp.core.CameraRecorder
import com.example.videojournalapp.presentation.viewmodels.RecorderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordScreen(navController: NavController, vm: RecorderViewModel = koinViewModel()){
    val state by vm.state.collectAsState()
    val context = LocalContext.current

    val controller = remember {
        createCameraController(context)
    }

    val recorder = remember {
        CameraRecorder(context, controller)
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->

            val camera = result[Manifest.permission.CAMERA] == true
            val audio = result[Manifest.permission.RECORD_AUDIO] == true

            vm.updatePermissionState(camera, audio)

            if (state.hasPermissions && state.isCameraRecording){
                recorder.startRecording{
                    vm.insertVideoEntry(it, state.description)
                }
            } else {
                vm.startCamera(false)
            }
        }

    val localLifeCycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(localLifeCycleOwner) {
        controller.bindToLifecycle(localLifeCycleOwner)
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.hasPermissions){
                CameraPreview(controller)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = state.description,
                    onValueChange = vm::onDescriptionChange,
                    placeholder = {
                        Text("Add description...")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(
                        onClick = {
                            permissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.RECORD_AUDIO
                                )
                            )
                            vm.startCamera(true)
                        },
                        enabled = !state.isCameraRecording
                    ) {
                        Icon(imageVector = Icons.Default.PlayArrow, "")


                    }

                    Button(
                        onClick = {
                            recorder.stopRecording()
                            vm.startCamera(false)
                        },
                        enabled = state.isCameraRecording
                    ) {
                        Icon(imageVector = Icons.Default.Stop, contentDescription = "")

                    }

                }

            }
        }

    }

    DisposableEffect(Unit) {
        onDispose {
            recorder.stopRecording()
        }

    }


}