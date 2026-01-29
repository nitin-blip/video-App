package com.example.videoapp.presentation.App

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.TextUnit
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoapp.presentation.HomeScreen.HomeScreen
import com.example.videoapp.presentation.viewModel.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun App(

    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel(),

    ) {

    val  mediaPermission = rememberPermissionState(permission = Manifest.permission.READ_MEDIA_VIDEO )
    val  mediaPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it){
            viewModel.showUi.value =  true
        }else{
            viewModel.showUi.value =  false
        }
    }

    LaunchedEffect(key1 = mediaPermission) {
        if(!mediaPermission.status.isGranted){
            mediaPermissionLauncher.launch(
                Manifest.permission.READ_MEDIA_VIDEO
            )

        }else{
            viewModel.showUi.value =  true
        }
    }

    val state =  viewModel.showUi.collectAsState()
    if(state.value){
        HomeScreen(navController = navController)
    }else{
        Box(
            modifier  = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Please Grant Permission")
        }
    }



    
}