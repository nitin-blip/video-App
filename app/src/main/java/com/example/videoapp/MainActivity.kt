package com.example.videoapp

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.videoapp.presentation.Navigation.AppNavigation
import com.example.videoapp.ui.theme.VideoAppTheme
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  MainScreen()

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen() {
    val  showSplace = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                showSplace.value =  false
            }, 3000
        )

    }
    if(showSplace.value){
       SplashScreen()
    }else{
        AppNavigation()
    }
}
@Composable
fun  SplashScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color =   Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment =  Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {

            Image(painter = painterResource(id  =  R.drawable.splase),  contentDescription =    "App Loading",
                modifier = Modifier.size(200.dp))
        }
        BasicText(
            text = "Welcome to the Video player ",
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        )

    }
}

