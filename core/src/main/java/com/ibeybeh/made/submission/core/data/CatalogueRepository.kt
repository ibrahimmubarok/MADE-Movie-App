package com.ibeybeh.made.submission.core.data

import com.ibeybeh.made.submission.core.data.local.LocalDataSource
import com.ibeybeh.made.submission.core.data.local.entity.mapMovieToEntities
import com.ibeybeh.made.submission.core.data.local.entity.mapResponseToEntities
import com.ibeybeh.made.submission.core.data.local.entity.mapResponsesToEntities
import com.ibeybeh.made.submission.core.data.local.entity.mapTvShowToEntities
import com.ibeybeh.made.submission.core.data.remote.RemoteDataSource
import com.ibeybeh.made.submission.core.data.remote.network.ApiResponse
import com.ibeybeh.made.submission.core.data.remote.response.MovieData
import com.ibeybeh.made.submission.core.data.remote.response.TvShowData
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.domain.model.mapEntitiesToMovie
import com.ibeybeh.made.submission.core.domain.model.mapEntitiesToTvShow
import com.ibeybeh.made.submission.core.domain.repository.ICatalogueRepository
import com.ibeybeh.made.submission.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {

    override fun getAllMovies(apiKey: String, page: Int, language: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieData>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map { list ->
                    list.map {
                        it.mapEntitiesToMovie()
                    }
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieData>>> {
                return remoteDataSource.getAllMovies(
                    apiKey = apiKey,
                    page = page,
                    language = language
                )
            }

            override suspend fun saveCallResult(data: List<MovieData>) {
                val movies = data.map {
                    it.mapResponsesToEntities()
                }
                localDataSource.insertMovie(movies)
            }
        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { list ->
            list.map {
                it.mapEntitiesToMovie()
            }
        }
    }

    override fun setMovieFavorite(movie: Movie) {
        val movieEntity = movie.mapMovieToEntities()
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity)
        }
    }

    override fun getAllTvShows(apiKey: String, page: Int, language: String): Flow<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<TvShowData>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShows().map { list ->
                    list.map {
                        it.mapEntitiesToTvShow()
                    }
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowData>>> {
                return remoteDataSource.getAllTvShows(
                    apiKey = apiKey,
                    page = page,
                    language = language
                )
            }

            override suspend fun saveCallResult(data: List<TvShowData>) {
                val tvShowList = data.map {
                    it.mapResponseToEntities()
                }
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()
    }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShows().map { list ->
            list.map {
                it.mapEntitiesToTvShow()
            }
        }
    }

    override fun setTvShowFavorite(tvShow: TvShow) {
        val tvShowEntity = tvShow.mapTvShowToEntities()
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShowEntity)
        }
    }
}