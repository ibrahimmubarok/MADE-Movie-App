package com.ibeybeh.made.submission.core.di

import androidx.room.Room
import com.ibeybeh.made.submission.core.data.CatalogueRepository
import com.ibeybeh.made.submission.core.data.local.LocalDataSource
import com.ibeybeh.made.submission.core.data.local.room.CatalogueDatabase
import com.ibeybeh.made.submission.core.data.remote.RemoteDataSource
import com.ibeybeh.made.submission.core.data.remote.network.ApiService
import com.ibeybeh.made.submission.core.domain.repository.ICatalogueRepository
import com.ibeybeh.made.submission.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.SECONDS

val databaseModule = module {
    factory { get<CatalogueDatabase>().catalogueDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CatalogueDatabase::class.java, "Catalogue.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICatalogueRepository> {
        CatalogueRepository(
            get(),
            get(),
            get()
        )
    }
}