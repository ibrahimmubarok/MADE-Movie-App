package com.ibeybeh.made.submission.core.domain.model

import android.os.Parcelable
import com.ibeybeh.made.submission.core.data.local.entity.TvShowEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String,
    val backdropPath: String,
    val firstAirDate: String,
    val isFavorite: Boolean
) : Parcelable

fun TvShowEntity.mapEntitiesToTvShow(): TvShow {
    return TvShow(
        id = id,
        name = name,
        overview = overview,
        voteAverage = voteAverage,
        isFavorite = isFavorite,
        posterPath = "https://image.tmdb.org/t/p/original/$posterPath",
        firstAirDate = firstAirDate,
        backdropPath = "https://image.tmdb.org/t/p/original/$backdropPath"
    )
}