package com.nikhil.movietime.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikhil.movietime.core.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAll(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM movies WHERE id = :movieId)")
    suspend fun exists(movieId: Int): Boolean

    @Delete
    suspend fun delete(movie: MovieEntity)
}