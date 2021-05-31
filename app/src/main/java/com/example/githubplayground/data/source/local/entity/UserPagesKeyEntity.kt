package com.example.githubplayground.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on : 31/05/21 | 22.40
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Entity(tableName = "userpagesentities")
data class UserPagesKeyEntity(
    @PrimaryKey
    val id: String,
    val currentPage: Int,
    val totalCount: Int,
    val updatedAt: Long = System.currentTimeMillis()
)