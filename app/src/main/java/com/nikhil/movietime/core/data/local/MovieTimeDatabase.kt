package com.nikhil.movietime.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikhil.movietime.core.data.local.entities.FavoriteMovieEntity
import com.nikhil.movietime.core.data.local.entities.HomeMovieEntity
import com.nikhil.movietime.core.data.local.entities.MovieDetailsEntity

@Database(
    entities = [
        FavoriteMovieEntity::class,
        HomeMovieEntity::class,
        MovieDetailsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieTimeDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}