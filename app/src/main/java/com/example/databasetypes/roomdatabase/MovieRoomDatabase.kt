package com.example.databasetypes.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1, exportSchema = false)
public abstract class MovieRoomDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MovieRoomDatabase::class.java,
            "movie_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}