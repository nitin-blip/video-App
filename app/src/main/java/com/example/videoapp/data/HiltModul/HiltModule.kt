package com.example.videoapp.data.HiltModul

import com.example.videoapp.data.Repo.VideoFileRepoImp
import com.example.videoapp.domain.rapo.VideoFileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object HiltModule {

    @Singleton
    @Provides
    fun  ProvideVideoFileRepo() : VideoFileRepo {
        return VideoFileRepoImp()
    }

}