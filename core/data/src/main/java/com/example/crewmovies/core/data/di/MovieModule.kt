package com.example.crewmovies.core.data.di

import com.example.crewmovies.core.data.apiservice.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class MovieModule {
    @Provides
    fun bindApiService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}