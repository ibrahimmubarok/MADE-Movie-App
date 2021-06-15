package com.ibeybeh.made.submission.cataloguefavorite.viewodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.domain.usecase.CatalogueUseCase

class FavoriteCatalogueViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getFavoriteMovies(): LiveData<List<Movie>> {
        return catalogueUseCase.getFavoriteMovies().asLiveData()
    }

    fun getFavoriteTvShows(): LiveData<List<TvShow>> {
        return catalogueUseCase.getFavoriteTvShows().asLiveData()
    }
}