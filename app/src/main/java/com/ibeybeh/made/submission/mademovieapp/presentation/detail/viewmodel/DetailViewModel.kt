package com.ibeybeh.made.submission.mademovieapp.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.domain.usecase.CatalogueUseCase

class DetailViewModel(private val catalogueUseCase: CatalogueUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie) {
        catalogueUseCase.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShow) {
        catalogueUseCase.setFavoriteTvShow(tvShow)
    }
}