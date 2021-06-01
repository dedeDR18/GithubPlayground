package com.example.githubplayground.data.source.remote.network

import com.example.githubplayground.data.source.remote.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created on : 30/05/21 | 22.55
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

interface ApiService {
    @Headers("authorization: 30863a2de018dc5c22eab36463d772de30e93231")
    @GET("/search/users?")
    fun doUserSearch(
            @Query("q", encoded = true) query: String,
            @Query("per_page") perPage: Int,
            @Query("page") page: Int
    ): Call<GithubResponse>

}