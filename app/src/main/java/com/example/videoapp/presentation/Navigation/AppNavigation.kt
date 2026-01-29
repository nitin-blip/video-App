package com.example.videoapp.presentation.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import com.example.videoapp.presentation.App.App
import com.example.videoapp.presentation.FolderScreen.FolderScreen
import com.example.videoapp.presentation.FolderVideo.FolderVideoScreen
import com.example.videoapp.presentation.HomeScreen.HomeScreen
import com.example.videoapp.presentation.VideoPalyerScreen.VideoPlayerScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun  AppNavigation() {

    val  navController = rememberNavController()
    NavHost(navController =  navController,  startDestination = NavigationItems.App){
        composable<NavigationItems.App>{
            App(navController = navController)


        }
        composable <NavigationItems.HomeScreen>{
            HomeScreen(navController )
        }

        composable<NavigationItems.PlayerScreen> { BackStackEntry ->

           val  url  :  NavigationItems.PlayerScreen =  BackStackEntry.toRoute()
            VideoPlayerScreen(url.VideoUrl,  navController = navController)
        }

        composable<NavigationItems.AllVideoFolder>{
            val  folderName  :  NavigationItems.AllVideoFolder =  it.toRoute()
            FolderScreen(navController =  navController)
        }

        composable <NavigationItems.FolderVideosScreen>{
            val  folderName : NavigationItems.FolderVideosScreen = it.toRoute()

            FolderVideoScreen(navController =  navController,  folderName = folderName.folderName)
        }


    }
}