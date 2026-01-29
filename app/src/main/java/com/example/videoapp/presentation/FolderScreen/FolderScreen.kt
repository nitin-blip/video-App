package com.example.videoapp.presentation.FolderScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.videoapp.presentation.Utils.folderCard
import com.example.videoapp.presentation.viewModel.ViewModel

@Composable
fun  FolderScreen(
    navController: NavHostController,
    viewModel: ViewModel = hiltViewModel()


) {

    val  videoFolder =  viewModel.FolderList.collectAsState().value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(videoFolder.toList()){(folderName , video) ->
            folderCard(
                folderName = folderName,
                videoCount = video.size,
                navController = navController

            )


        }


    }
}