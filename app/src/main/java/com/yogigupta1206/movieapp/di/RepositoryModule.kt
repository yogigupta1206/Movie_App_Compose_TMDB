package com.yogigupta1206.movieapp.di

import com.yogigupta1206.movieapp.data.data_source.network.MovieApi
import com.yogigupta1206.movieapp.data.data_source.network.NetworkHelper
import com.yogigupta1206.movieapp.data.repository.MovieRepoImpl
import com.yogigupta1206.movieapp.domain.repository.MovieRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieApi: MovieApi, networkHelper: NetworkHelper) : MovieRepo {
        return MovieRepoImpl(movieApi, networkHelper)
    }

}