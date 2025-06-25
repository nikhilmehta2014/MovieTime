package com.nikhil.movietime.di

import com.nikhil.movietime.core.util.DefaultStringProvider
import com.nikhil.movietime.core.util.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StringProviderModule {

    @Binds
    abstract fun bindStringProvider(
        impl: DefaultStringProvider
    ): StringProvider
}
