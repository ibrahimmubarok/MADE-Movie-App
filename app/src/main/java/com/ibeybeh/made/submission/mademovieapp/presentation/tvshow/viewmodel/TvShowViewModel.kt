package com.ibeybeh.made.submission.mademovieapp.presentation.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.domain.usecase.CatalogueUseCase

class TvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getAllTvShows(apiKey: String, page: Int, language: String): LiveData<Resource<List<TvShow>>> {
        return catalogueUseCase.getAllTvShows(
            apiKey = apiKey,
            page = page,
            language = language
        ).asLiveData()
    }
}