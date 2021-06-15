package com.ibeybeh.made.submission.mademovieapp.di

import com.ibeybeh.made.submission.core.domain.usecase.CatalogueInteractor
import com.ibeybeh.made.submission.core.domain.usecase.CatalogueUseCase
import com.ibeybeh.made.submission.mademovieapp.presentation.detail.viewmodel.DetailViewModel
import com.ibeybeh.made.submission.mademovieapp.presentation.movie.viewmodel.MovieViewModel
import com.ibeybeh.made.submission.mademovieapp.presentation.tvshow.viewmodel.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatalogueUseCase> { CatalogueInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}