package com.ibeybeh.made.submission.core.data.local

import com.ibeybeh.made.submission.core.data.local.entity.MovieEntity
import com.ibeybeh.made.submission.core.data.local.entity.TvShowEntity
import com.ibeybeh.made.submission.core.data.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mCatalogueDao: CatalogueDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = mCatalogueDao.getAllMovies()

    fun getAllTvShows(): Flow<List<TvShowEntity>> = mCatalogueDao.getAllTvShows()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mCatalogueDao.getFavoriteMovies()

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> = mCatalogueDao.getFavoriteTvShows()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mCatalogueDao.insertMovie(movieList)

    suspend fun insertTvShow(tvShowList: List<TvShowEntity>) = mCatalogueDao.insertTvShow(tvShowList)

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        mCatalogueDao.updateFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        mCatalogueDao.updateFavoriteTvShow(tvShow)
    }
}