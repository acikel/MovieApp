package com.example.crewmovies.di

import com.example.crewmovies.core.data.apiservice.MovieService
import com.example.crewmovies.core.data.di.NetworkModule
import com.example.crewmovies.core.data.mappers.PeopleMapper
import com.example.crewmovies.core.data.repositories.PeopleRepositoryImpl
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PeopleRepositoryModule {
    @Provides
    fun providePeopleRepository(
        peopleMapper : PeopleMapper,
        movieService : MovieService
    ) : PeopleRepository{
        return PeopleRepositoryImpl(movieService, peopleMapper)
    }
}