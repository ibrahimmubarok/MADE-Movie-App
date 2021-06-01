package com.ibeybeh.made.submission.core.domain.repository

import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ICatalogueRepository {

    fun getAllMovies(apiKey: String, page: Int, language: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie)

    fun getAllTvShows(apiKey: String, page: Int, language: String): Flow<Resource<List<TvShow>>>

    fun getFavoriteTvShows(): Flow<List<TvShow>>

    fun setTvShowFavorite(tvShow: TvShow)
}