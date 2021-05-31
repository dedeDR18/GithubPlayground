package com.example.githubplayground.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created on : 31/05/21 | 23.37
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Parcelize
data class UserPagesKey(
    val id: String,
    val currentPage: Int,
    val totalCount: Int,
    val updatedAt: Long
): Parcelable