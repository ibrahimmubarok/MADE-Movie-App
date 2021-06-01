package com.ibeybeh.made.submission.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibeybeh.made.submission.core.data.remote.response.MovieData
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.utils.emptyString

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)

fun MovieData.mapResponsesToEntities(): MovieEntity {
    return MovieEntity(
        id = id ?: 0,
        title = title ?: emptyString(),
        overview = overview ?: emptyString(),
        voteAverage = voteAverage ?: 0.0,
        posterPath = posterPath ?: emptyString(),
        releaseDate = releaseDate ?: emptyString(),
        backdropPath = backdropPath ?: emptyString(),
        isFavorite = false
    )
}

fun Movie.mapMovieToEntities(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        voteAverage = voteAverage,
        posterPath = posterPath,
        releaseDate = releaseDate,
        backdropPath = backdropPath,
        isFavorite = isFavorite
    )
}