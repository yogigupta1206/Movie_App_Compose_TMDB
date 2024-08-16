package com.yogigupta1206.movieapp.data.repository

import android.util.Log
import com.yogigupta1206.movieapp.data.data_source.network.MovieApi
import com.yogigupta1206.movieapp.data.data_source.network.NetworkHelper
import com.yogigupta1206.movieapp.data.data_source.network.NetworkResult
import com.yogigupta1206.movieapp.domain.model.MovieApiEntity
import com.yogigupta1206.movieapp.domain.repository.MovieRepo
import com.yogigupta1206.movieapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepoImpl@Inject constructor(
    private val movieApi: MovieApi,
    private val networkHelper: NetworkHelper
) : MovieRepo {

    companion object{
        val TAG: String = MovieRepoImpl::class.java.simpleName
    }

    override fun getMovieData(): Flow<Resource<MovieApiEntity>> = flow {
        emit(Resource.Loading())
        when(val response = getMovieDataFromNetwork()){
            is NetworkResult.Error -> {
                Log.d(TAG, "getMovieData: Error: ${response.message}")
                emit(Resource.Error(response.message ?: "Unknown error", response.responseData))
            }
            is NetworkResult.Success -> {
                Log.d(TAG, "getMovieData: Success")
                emit(Resource.Success(response.responseData?: MovieApiEntity()))
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getMovieDataFromNetwork() = networkHelper.safeApiCall { movieApi.getMovieData() }


}