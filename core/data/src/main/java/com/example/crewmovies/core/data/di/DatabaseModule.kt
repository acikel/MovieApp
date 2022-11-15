package com.example.crewmovies.core.data.di

import android.content.Context
import androidx.room.Room
import com.example.crewmovies.core.data.db.CrewRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesCrewRoomDatabase(
        @ApplicationContext context: Context,
    ): CrewRoomDatabase = Room.databaseBuilder(
        context,
        CrewRoomDatabase::class.java,
        "crew-room-database"
    ).build()
}