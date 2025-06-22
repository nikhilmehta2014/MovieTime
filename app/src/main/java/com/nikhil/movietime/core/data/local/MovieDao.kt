package com.nikhil.movietime.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikhil.movietime.core.data.local.entities.FavoriteMovieEntity
import com.nikhil.movietime.core.data.local.entities.HomeMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM movies_favorite")
    fun getAllMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM movies_favorite WHERE id = :movieId)")
    suspend fun movieExists(movieId: Int): Boolean

    @Delete
    suspend fun deleteMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM movies_home WHERE isTrending = 1")
    fun getTrendingMovies(): Flow<List<HomeMovieEntity>>

    @Query("SELECT * FROM movies_home WHERE isNowPlaying = 1")
    fun getNowPlayingMovies(): Flow<List<HomeMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<HomeMovieEntity>)

    @Query("DELETE FROM movies_home WHERE isTrending = 1 OR isNowPlaying = 1")
    suspend fun clearHomeMovies()
}