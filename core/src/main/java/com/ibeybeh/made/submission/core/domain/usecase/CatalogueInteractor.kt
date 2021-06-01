package com.ibeybeh.made.submission.core.domain.usecase

import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.domain.repository.ICatalogueRepository
import kotlinx.coroutines.flow.Flow

class CatalogueInteractor(private val iCatalogueRepository: ICatalogueRepository) : CatalogueUseCase {

    override fun getAllMovies(apiKey: String, page: Int, language: String): Flow<Resource<List<Movie>>> {
        return iCatalogueRepository.getAllMovies(
            apiKey = apiKey,
            page = page,
            language = language
        )
    }

    override fun getAllTvShows(apiKey: String, page: Int, language: String): Flow<Resource<List<TvShow>>> {
        return iCatalogueRepository.getAllTvShows(
            apiKey = apiKey,
            page = page,
            language = language
        )
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return iCatalogueRepository.getFavoriteMovies()
    }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> {
        return iCatalogueRepository.getFavoriteTvShows()
    }

    override fun setFavoriteMovie(movie: Movie) {
        iCatalogueRepository.setMovieFavorite(movie)
    }

    override fun setFavoriteTvShow(tvShow: TvShow) {
        iCatalogueRepository.setTvShowFavorite(tvShow)
    }
}