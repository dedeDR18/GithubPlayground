package com.example.githubplayground.data

import android.util.Log
import com.example.githubplayground.data.source.local.entity.UserPagesKeyEntity
import com.example.githubplayground.data.source.local.room.GithubDatabase
import com.example.githubplayground.data.source.remote.network.ApiService
import com.example.githubplayground.domain.model.User
import com.example.githubplayground.domain.model.UserPagesKey
import com.example.githubplayground.domain.repository.IRepository
import com.example.githubplayground.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.net.URLEncoder

/**
 * Created on : 30/05/21 | 22.54
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class Repository(private val apiService: ApiService, val githubDatabase: GithubDatabase) :
    IRepository {
    override fun getUser(query: String, perPage: Int, page: Int) = flow<Resource<List<User>>> {
        emit(Resource.Loading())
        try {
            val response =
                apiService.doUserSearch(query.plus("+in:login"), perPage, page).awaitResponse()
            if (response.isSuccessful) {
                val listUser = response.body()?.items
                val totalCount = response.body()?.total_count!!
                listUser?.let {
                    emit(Resource.Success(DataMapper.mapUserResponseToUserDomain(it)))
                }
                //do insertPageKey to database
                githubDatabase.userPagesKeyDao().saveUserPageKeys(
                    listOf(
                        UserPagesKeyEntity(
                            query.plus("+in:login"),
                            page,
                            totalCount
                        )
                    )
                )

            } else {
                Log.d("TAG", "error api...")
            }
        } catch (e: Exception) {
            emit(Resource.Error("${e.message}"))
            Log.d("TAG", "error exception = ${e.message}")
        }
    }

    override fun getCurrentPage() = flow<UserPagesKey> {
        githubDatabase.userPagesKeyDao().getUserPageKey().collect {

            emit(DataMapper.mapUserPageEntityToUserPageDomain(it))
        }
    }

    override fun clearPageData(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            githubDatabase.userPagesKeyDao().clearUserPageKey()
        }
    }

}