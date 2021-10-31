package com.example.databasetypes.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    /*private class MovieRoomDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.movieDao())
                }
            }
        }*/

    /*private fun populateDatabase(movieDao: MovieDao) {
        //deletes everything before start
        movieDao.deleteAll()

        var movie = Movie (
            "1",
            "https://m.media-amazon.com/images/I/71nWfxOlCTL._AC_SL1398_.jpg",
            "Dune (2021)",
            "8.1",
            "8",
            "82"
        )
        movieDao.insert(movie)
    }*/
    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MovieRoomDatabase::class.java,
                "movie_database"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            return instance
        }
    }
}
// Database class after the version update.

