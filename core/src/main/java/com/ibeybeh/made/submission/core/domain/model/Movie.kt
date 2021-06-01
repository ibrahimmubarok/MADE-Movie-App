package com.ibeybeh.made.submission.core.domain.model

import android.os.Parcelable
import com.ibeybeh.made.submission.core.data.local.entity.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String,
    val releaseDate: String,
    val backdropPath: String,
    val isFavorite: Boolean
) : Parcelable

fun MovieEntity.mapEntitiesToMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        voteAverage = voteAverage,
        isFavorite = isFavorite,
        posterPath = "https://image.tmdb.org/t/p/original/$posterPath",
        releaseDate = releaseDate,
        backdropPath = "https://image.tmdb.org/t/p/original/$backdropPath"
    )
}