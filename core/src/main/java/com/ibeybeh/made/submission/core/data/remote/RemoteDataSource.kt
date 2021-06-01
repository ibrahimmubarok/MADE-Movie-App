package com.ibeybeh.made.submission.core.data.remote

import com.ibeybeh.made.submission.core.data.remote.network.ApiResponse
import com.ibeybeh.made.submission.core.data.remote.network.ApiService
import com.ibeybeh.made.submission.core.data.remote.response.MovieData
import com.ibeybeh.made.submission.core.data.remote.response.TvShowData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(apiKey: String, page: Int, language: String): Flow<ApiResponse<List<MovieData>>> {
        return flow {
            try {
                val response = apiService.getAllMovies(apiKey, page, language)
                val movieList = response.movies
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.movies))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShows(apiKey: String, page: Int, language: String): Flow<ApiResponse<List<TvShowData>>> {
        return flow {
            try {
                val response = apiService.getAllTvShows(apiKey, page, language)
                val tvShowList = response.tvShows
                if (tvShowList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.tvShows))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}