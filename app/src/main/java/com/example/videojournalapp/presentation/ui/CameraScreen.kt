package com.example.videojournalapp.presentation.ui
//
//import android.Manifest
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.example.videojournalapp.core.CameraRecorder
//
//@Composable
//fun CameraScreen(
//    onVideoSaved: (Uri) -> Unit
//) {
//
//    val context = LocalContext.current
//
//    val controller = remember {
//        createCameraController(context)
//    }
//
//    val recorder = remember {
//        CameraRecorder(context, controller)
//    }
//
//    val cameraPermissionGranted by remember {
//        mutableStateOf(false)
//    }
//
//    val audioPermissionGranted by remember {
//        mutableStateOf(false)
//    }
//
//    val permissionLauncher =
//        rememberLauncherForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { result ->
//
//            val camera = result[Manifest.permission.CAMERA] == true
//            val audio = result[Manifest.permission.RECORD_AUDIO] == true
//
//            // update state
//        }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//
//        CameraPreview(controller)
//
//        Column(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//        ) {
//
//            Button(
//                onClick = {
//                    recorder.startRecording(onVideoSaved)
//                }
//            ) {
//                Text("Start recording")
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Button(
//                onClick = {
//                    recorder.stopRecording()
//                }
//            ) {
//                Text("Stop")
//            }
//        }
//    }
//}