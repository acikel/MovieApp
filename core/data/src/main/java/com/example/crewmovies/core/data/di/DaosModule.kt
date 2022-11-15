package com.example.crewmovies.core.data.di

import com.example.crewmovies.core.data.db.CrewRoomDatabase
import com.example.crewmovies.core.data.db.dao.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesAuthorDao(
        database: CrewRoomDatabase,
    ): PersonDao = database.personDao()
}
