package com.ibeybeh.made.submission.core.data.remote.network

import com.ibeybeh.made.submission.core.data.remote.response.MovieResponse
import com.ibeybeh.made.submission.core.data.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getAllTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): TvShowResponse
}