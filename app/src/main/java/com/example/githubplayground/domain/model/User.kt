package com.example.githubplayground.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created on : 30/05/21 | 23.00
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Parcelize
data class User(
        val avatar_url: String,
        val html_url: String,
        val id: Int,
        val login: String,
        val repos_url: String,
        val score: Int,
        val type: String,
        val url: String
): Parcelable