package com.example.githubplayground.domain.repository

import com.example.githubplayground.data.Resource
import com.example.githubplayground.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 30/05/21 | 23.00
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface IRepository {
    fun getUser(query: String, perPage:Int, page:Int): Flow<Resource<List<User>>>
}