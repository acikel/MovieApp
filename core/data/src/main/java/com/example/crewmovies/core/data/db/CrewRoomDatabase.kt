package com.example.crewmovies.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crewmovies.core.data.db.dao.PersonDao
import com.example.crewmovies.core.data.db.model.PersonEntity

@Database(entities = [(PersonEntity::class)], version = 1)
abstract class CrewRoomDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {

        private var INSTANCE: CrewRoomDatabase? = null

        fun getInstance(context: Context): CrewRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CrewRoomDatabase::class.java,
                        "crew_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}