package com.nikhil.movietime.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_home")
data class HomeMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String?,
    val isTrending: Boolean = false,
    val isNowPlaying: Boolean = false,
    val lastUpdated: Long = 0L
)