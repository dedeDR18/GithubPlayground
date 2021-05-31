package com.example.githubplayground.data.source.remote.response

data class GithubResponse(
    val incomplete_results: Boolean,
    val userResponseItems: List<UserResponseItem>,
    val total_count: Int
)