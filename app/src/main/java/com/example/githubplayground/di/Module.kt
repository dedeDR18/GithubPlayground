package com.example.githubplayground.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.githubplayground.data.Repository
import com.example.githubplayground.data.source.local.entity.UserPagesKeyEntity
import com.example.githubplayground.data.source.local.room.GithubDatabase
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
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created on : 31/05/21 | 21.26
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
private val BASE_URL = "https://api.github.com/"

val databaseModule = module {
    factory { get<GithubDatabase>().userPagesKeyDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java,
            "githubdatabase.db"
        ).fallbackToDestructiveMigration()
//            .addCallback(object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    Executors.newSingleThreadScheduledExecutor().execute(Runnable {
//                    get<GithubDatabase>().userPagesKeyDao().saveUserPageKeys(UserPagesKeyEntity(
//                        " ",
//                        currentPage = 1,
//                        totalCount = 2
//                    ))
//                    })
//                }
//            })
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
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)
}

