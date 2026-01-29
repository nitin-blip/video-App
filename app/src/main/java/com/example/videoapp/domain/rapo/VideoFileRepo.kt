package com.example.videoapp.domain.rapo

import android.app.Application
import com.example.videoapp.BaseApplication
import com.example.videoapp.data.model.VideoFile
import kotlinx.coroutines.flow.Flow

interface VideoFileRepo {
    suspend fun   getAllVideos(application: Application): Flow<ArrayList<VideoFile>>
    suspend fun  getAllFolder(application: Application): Flow<Map<String, List<VideoFile>>>
}