package com.example.githubplayground.utils

import com.example.githubplayground.data.source.local.entity.UserPagesKeyEntity
import com.example.githubplayground.data.source.remote.response.UserResponseItem
import com.example.githubplayground.domain.model.User
import com.example.githubplayground.domain.model.UserPagesKey

/**
 * Created on : 31/05/21 | 22.24
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
object DataMapper {
    fun mapUserResponseToUserDomain(input: List<UserResponseItem>): List<User> {
        val listUser = ArrayList<User>()
        input.map { input ->
            val u = User(
                avatar_url = input.avatar_url,
                html_url = input.html_url,
                id = input.id,
                login = input.login,
                repos_url = input.repos_url,
                score = input.score,
                type = input.type,
                url = input.url
            )
            listUser.add(u)
        }
        return listUser
    }

    fun mapUserPageEntityToUserPageDomain(input: UserPagesKeyEntity) = UserPagesKey(
        id = input.id,
        currentPage = input.currentPage,
        totalCount = input.totalCount,
        updatedAt = input.updatedAt
    )

}