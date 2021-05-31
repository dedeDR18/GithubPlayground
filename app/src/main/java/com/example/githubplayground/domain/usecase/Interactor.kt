package com.example.githubplayground.domain.usecase

import com.example.githubplayground.data.Repository
import com.example.githubplayground.data.Resource
import com.example.githubplayground.domain.model.User
import com.example.githubplayground.domain.model.UserPagesKey
import com.example.githubplayground.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 30/05/21 | 22.59
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
class Interactor(private val repository: IRepository) : Usecase {
    override fun doSearchUser(query: String, page: Int) =
        repository.getUser(query = query, page = page)

    override fun doGetCurrentPage(query: String): Flow<UserPagesKey> =
        repository.getCurrentPage(query)

}