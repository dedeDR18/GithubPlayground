package com.example.githubplayground.domain.usecase

import com.example.githubplayground.data.Resource
import com.example.githubplayground.domain.model.User
import com.example.githubplayground.domain.model.UserPagesKey
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 30/05/21 | 22.59
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface Usecase {
    fun doSearchUser(query:String, page: Int): Flow<Resource<List<User>>>
    fun doGetCurrentPage(query:String): Flow<UserPagesKey>
}