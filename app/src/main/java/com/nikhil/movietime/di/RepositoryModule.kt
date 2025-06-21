package com.nikhil.movietime.di

import com.nikhil.movietime.core.network.ApiService
import com.nikhil.movietime.ui.home.domain.repository.HomeRepository
import com.nikhil.movietime.ui.home.data.repository.HomeRepositoryImpl
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
}