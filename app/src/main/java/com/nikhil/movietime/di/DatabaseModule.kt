package com.nikhil.movietime.di

import android.content.Context
import androidx.room.Room
import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.data.local.MovieTimeDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): MovieTimeDatabase =
        Room.databaseBuilder(
            context,
            MovieTimeDatabase::class.java,
            "movie_db"
        ).build()

    @Provides
    fun provideMovieDao(db: MovieTimeDatabase): MovieDao = db.movieDao()
}