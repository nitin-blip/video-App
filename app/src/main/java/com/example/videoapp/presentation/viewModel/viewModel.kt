package com.example.videoapp.presentation.viewModel

import android.app.Application
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoapp.data.model.VideoFile
import com.example.videoapp.domain.rapo.VideoFileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModel  @Inject constructor(val  repo: VideoFileRepo,  val  application: Application) :  ViewModel(){


    val  showUi = MutableStateFlow(false)
    val  videoList = MutableStateFlow(emptyList<VideoFile>())
    val  FolderList = MutableStateFlow(emptyMap<String,List<VideoFile>>())
    var  isLoading =  MutableStateFlow(false)

    fun  LoadVideoFiles(){
        isLoading.value =  true
        viewModelScope.launch {
            repo.getAllVideos(application).collectLatest {
                videoList.value = it

            }
        }
        isLoading.value =  false

    }

    fun  LoadVideofolder(){

        isLoading.value =  true
         viewModelScope.launch {
             repo.getAllFolder(application).collectLatest {

                 FolderList.value  =  it
             }
         }
        isLoading.value = false

    }

    init {
        viewModelScope.launch {
            LoadVideoFiles()
            LoadVideofolder()
        }
    }



}