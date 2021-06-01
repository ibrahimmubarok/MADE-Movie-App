package com.ibeybeh.made.submission.core.domain.usecase

import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface CatalogueUseCase {

    fun getAllMovies(apiKey: String, page: Int, language: String): Flow<Resource<List<Movie>>>

    fun getAllTvShows(apiKey: String, page: Int, language: String): Flow<Resource<List<TvShow>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getFavoriteTvShows(): Flow<List<TvShow>>

    fun setFavoriteMovie(movie: Movie)

    fun setFavoriteTvShow(tvShow: TvShow)
}