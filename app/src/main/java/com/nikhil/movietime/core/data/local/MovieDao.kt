package com.nikhil.movietime.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikhil.movietime.core.data.local.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM movies_favorite")
    fun getAll(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM movies_favorite WHERE id = :movieId)")
    suspend fun exists(movieId: Int): Boolean

    @Delete
    suspend fun delete(movie: FavoriteMovieEntity)
}