package com.nikhil.movietime.core.data.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromGenreList(genres: List<String>): String {
        return genres.joinToString(separator = ",")
    }

    @TypeConverter
    fun toGenreList(data: String): List<String> {
        return if (data.isEmpty()) emptyList() else data.split(",")
    }
}
