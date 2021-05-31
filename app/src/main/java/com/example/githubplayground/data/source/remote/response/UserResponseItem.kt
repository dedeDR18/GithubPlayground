package com.example.githubplayground.data.source.remote.response

data class UserResponseItem(
    val avatar_url: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val repos_url: String,
    val score: Int,
    val type: String,
    val url: String
)