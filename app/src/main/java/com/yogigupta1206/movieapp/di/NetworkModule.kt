package com.yogigupta1206.movieapp.di

import android.content.Context
import com.yogigupta1206.movieapp.data.data_source.network.MovieApi
import com.yogigupta1206.movieapp.data.data_source.network.NetworkHelper
import com.yogigupta1206.movieapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val authInterceptor = Interceptor { chain ->
        val req = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxOGI4OWVlODU5OGIzNjdkYzNmZDQ3MzQ5MDVhYjliZSIsIm5iZiI6MTcyMzc4MjIyMC40Nzg0MjksInN1YiI6IjY2YmVkMzc3OWEwMzA0Yzk1ODU1YWQzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eX4325sGSodSJ8YVRGjGza9wSV0lGPJNznQnwSY1Cag"
        ).build()
        chain.proceed(req)
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context) = NetworkHelper(context)


}
