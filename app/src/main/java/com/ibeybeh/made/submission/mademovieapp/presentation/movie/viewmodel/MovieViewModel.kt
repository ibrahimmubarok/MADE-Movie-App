package com.ibeybeh.made.submission.mademovieapp.presentation.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.usecase.CatalogueUseCase

class MovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getAllMovies(apiKey: String, page: Int, language: String): LiveData<Resource<List<Movie>>> {
        return catalogueUseCase.getAllMovies(
            apiKey = apiKey,
            page = page,
            language = language
        ).asLiveData()
    }
}