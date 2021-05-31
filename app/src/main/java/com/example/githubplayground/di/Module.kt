package com.example.githubplayground.di

import androidx.room.Room
import com.example.githubplayground.data.Repository
import com.example.githubplayground.data.source.local.room.GithubDatabase
import com.example.githubplayground.data.source.local.room.UserPagesKeyDao
import com.example.githubplayground.data.source.remote.network.ApiService
import com.example.githubplayground.domain.repository.IRepository
import com.example.githubplayground.domain.usecase.Interactor
import com.example.githubplayground.domain.usecase.Usecase
import com.example.githubplayground.presentation.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

/**
 * Created on : 31/05/21 | 21.26
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

val databaseModule = module {
    factory { get<GithubDatabase>().userPagesKeyDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java,
            "githubdatabase.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single<IRepository> { Repository(get(), get()) }
}

val usecaseModule = module {
    factory<Usecase> { Interactor(get()) }
}

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
}

private fun createRetrofit(client: OkHttpClient): ApiService {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)
}

