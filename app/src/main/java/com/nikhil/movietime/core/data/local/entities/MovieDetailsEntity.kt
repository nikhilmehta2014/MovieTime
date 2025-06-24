package com.nikhil.movietime.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val tagline: String?,
    val overview: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val rating: Float?,
    val runtime: String?,
    val releaseYear: String?,
    val adult: String?,
    val genres: List<String> = emptyList(),
    val isTrending: Boolean = false,
    val isNowPlaying: Boolean = false,
    val lastUpdated: Long = 0L
)