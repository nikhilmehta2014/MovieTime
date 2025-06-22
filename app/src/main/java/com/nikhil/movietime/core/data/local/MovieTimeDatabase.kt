package com.nikhil.movietime.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.movietime.core.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieTimeDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}