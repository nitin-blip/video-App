package com.example.videoapp.data.Repo

import android.app.Application
import android.content.ContentUris
import android.provider.MediaStore
import com.example.videoapp.data.model.VideoFile
import com.example.videoapp.domain.rapo.VideoFileRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.File

class VideoFileRepoImp : VideoFileRepo {
    override suspend fun getAllVideos(application: Application): Flow<ArrayList<VideoFile>> {

        val  allVideo = ArrayList<VideoFile>()
        val  projection =  arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DISPLAY_NAME,


        )
        val  uri =  MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val  memoryCursor =  application.contentResolver.query(uri,projection,null, null, null)
        if(memoryCursor != null){
            while (memoryCursor.moveToNext()){
                val  id = memoryCursor.getString(0)
                val  path= memoryCursor.getString(1)
                val  title= memoryCursor.getString(2)
                val  size= memoryCursor.getString(3)
                val  dateAdded= memoryCursor.getString(4)
                val  duration= memoryCursor.getString(5)
                val  fileName= memoryCursor.getString(6)

                val  contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id.toLong()
                )
                val  videoFile = VideoFile(
                    id = id,
                    path = path,
                    title = title,
                    size =  size,
                    dateAdded = dateAdded,
                    duration = duration,
                    fileName = fileName,
                    thumbnailUrl = contentUri.toString()
                )
                allVideo.add(videoFile)
            }
            memoryCursor.close()
        }
        return flow {
            emit(allVideo)
        }
    }

    override suspend fun getAllFolder(application: Application): Flow<Map<String, List<VideoFile>>> {
        val allVideos = getAllVideos(application).first()
        val  videosByFolders = allVideos.groupBy { File(it.path).parent?:"Unknown Folder" }
        return flow {
            emit(videosByFolders)
        }

    }
}