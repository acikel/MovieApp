package com.example.crewmovies.core.data.di

import com.example.crewmovies.core.data.apiservice.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class MovieModule {
    @Provides
    @Singleton
    fun bindApiService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
