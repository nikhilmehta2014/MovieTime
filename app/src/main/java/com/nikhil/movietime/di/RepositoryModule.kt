package com.nikhil.movietime.di

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.data.repository.FavoriteRepository
import com.nikhil.movietime.core.data.repository.MovieRepositoryImpl
import com.nikhil.movietime.core.domain.repository.FavoriteRepositoryImpl
import com.nikhil.movietime.core.domain.repository.MovieRepository
import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.moviedetails.data.repository.MovieDetailsRepositoryImpl
import com.nikhil.movietime.ui.moviedetails.domain.repository.MovieDetailsRepository
import com.nikhil.movietime.ui.search.data.repository.SearchRepositoryImpl
import com.nikhil.movietime.ui.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiService, dao: MovieDao): MovieRepository {
        return MovieRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(api: ApiService, dao: MovieDao): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api: ApiService): SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(dao: MovieDao): FavoriteRepository {
        return FavoriteRepositoryImpl(dao)
    }
}