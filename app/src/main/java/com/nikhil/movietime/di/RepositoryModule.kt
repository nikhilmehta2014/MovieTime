package com.nikhil.movietime.di

import com.nikhil.movietime.core.data.local.MovieDao
import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.home.domain.repository.HomeRepository
import com.nikhil.movietime.ui.home.data.repository.HomeRepositoryImpl
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
    fun provideMovieRepository(api: ApiService): HomeRepository {
        return HomeRepositoryImpl(api)
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
}