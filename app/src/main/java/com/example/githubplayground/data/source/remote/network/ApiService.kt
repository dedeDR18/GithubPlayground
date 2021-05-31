package com.example.githubplayground.data.source.remote.network

import com.example.githubplayground.data.source.remote.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on : 30/05/21 | 22.55
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

interface ApiService {
    @GET("/search/users?")
    fun doUserSearch(
            @Query("q") query: String,
            @Query("per_page") perPage: Int,
            @Query("page") page: Int
    ): Call<GithubResponse>

}