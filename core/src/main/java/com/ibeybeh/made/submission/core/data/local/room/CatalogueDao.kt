package com.ibeybeh.made.submission.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ibeybeh.made.submission.core.data.local.entity.MovieEntity
import com.ibeybeh.made.submission.core.data.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_show")
    fun getAllTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show where isFavorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Update
    fun updateFavoriteTvShow(tvShow: TvShowEntity)
}