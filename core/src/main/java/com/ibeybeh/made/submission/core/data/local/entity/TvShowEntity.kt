package com.ibeybeh.made.submission.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibeybeh.made.submission.core.data.remote.response.TvShowData
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.utils.emptyString

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)

fun TvShowData.mapResponseToEntities(): TvShowEntity {
    return TvShowEntity(
        id = id ?: 0,
        name = name ?: emptyString(),
        overview = overview ?: emptyString(),
        voteAverage = voteAverage ?: 0.0,
        posterPath = posterPath ?: emptyString(),
        firstAirDate = firstAirDate ?: emptyString(),
        backdropPath = backdropPath ?: emptyString(),
        isFavorite = false
    )
}

fun TvShow.mapTvShowToEntities(): TvShowEntity {
    return TvShowEntity(
        id = id,
        name = name,
        overview = overview,
        voteAverage = voteAverage,
        posterPath = posterPath,
        firstAirDate = firstAirDate,
        backdropPath = backdropPath,
        isFavorite = isFavorite
    )
}