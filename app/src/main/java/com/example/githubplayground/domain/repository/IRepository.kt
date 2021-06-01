package com.example.githubplayground.domain.repository

import com.example.githubplayground.data.Resource
import com.example.githubplayground.domain.model.User
import com.example.githubplayground.domain.model.UserPagesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 30/05/21 | 23.00
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface IRepository {
    fun getUser(query: String, perPage:Int = 20, page:Int): Flow<Resource<List<User>>>
    fun getCurrentPage(): Flow<UserPagesKey>
    fun clearPageData(coroutineScope: CoroutineScope)
}