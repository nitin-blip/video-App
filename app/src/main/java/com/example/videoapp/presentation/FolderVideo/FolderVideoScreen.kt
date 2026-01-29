package com.example.videoapp.presentation.FolderVideo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoapp.presentation.Utils.CustomTopAppBar
import com.example.videoapp.presentation.Utils.VideoCard
import com.example.videoapp.presentation.viewModel.ViewModel


@Composable
fun  FolderVideoScreen(
    navController: NavHostController,
    folderName: String,
    viewModel: ViewModel = hiltViewModel()
) {

    Scaffold(topBar = {
        CustomTopAppBar(topAppBarText = folderName, navController = navController)
    }) { innerPadding ->
         val  videoFolder =  viewModel.FolderList.collectAsState().value
        val  videosInFolder =  videoFolder[folderName]?: emptyList()


        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement =  Arrangement.spacedBy(16.dp)
        ) {

            items(videosInFolder){video ->

                VideoCard(
                    path = video.path?:"untitled", ///  make  the  change
                    title = video.title?:"Untitled ",
                    size = video.size,
                    duration = video.duration,
                    dateAdded = video.dateAdded,
                    fileNames = video.fileName,
                    thumbnail = video.thumbnailUrl?: "Unknow",
                    id = video.id?: "Unknow",
                    navController = navController
                )

            }
        }

    }
}