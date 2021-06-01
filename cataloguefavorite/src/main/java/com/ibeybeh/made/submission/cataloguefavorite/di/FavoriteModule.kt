package com.ibeybeh.made.submission.cataloguefavorite.di

import com.ibeybeh.made.submission.cataloguefavorite.viewodel.FavoriteCatalogueViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteCatalogueViewModel((get())) }
}