package com.example.githubplayground.data

import android.util.Log
import com.example.githubplayground.data.source.remote.network.ApiService
import com.example.githubplayground.domain.model.User
import com.example.githubplayground.domain.repository.IRepository
import com.example.githubplayground.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse

/**
 * Created on : 30/05/21 | 22.54
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class Repository(private val apiService: ApiService) : IRepository {
    override fun getUser(query: String, perPage: Int, page: Int) = flow<Resource<List<User>>> {
        emit(Resource.Loading())
        try {
            val response = apiService.doUserSearch(query, perPage, page).awaitResponse()
            if (response.isSuccessful) {
                val listUser = response.body()?.userResponseItems
                val totalCount = response.body()?.total_count
                val currentPage = page
                val nextPage = page + 1
                listUser?.let {
                    emit(Resource.Success(DataMapper.mapUserResponseToUserDomain(it)))
                }
                //do insertPageKey to database

            } else {
                Log.d("TAG", "error api...")
            }
        } catch (e: Exception) {
            emit(Resource.Error("${e.message}"))
            Log.d("TAG", "error exception = ${e.message}")
        }
    }

}