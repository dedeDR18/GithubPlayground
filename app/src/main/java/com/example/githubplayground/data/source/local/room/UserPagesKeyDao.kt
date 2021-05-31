package com.example.githubplayground.data.source.local.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubplayground.data.source.local.entity.UserPagesKey
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 31/05/21 | 23.07
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
interface UserPagesKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserPageKeys(moviePagesKey: List<UserPagesKey>)

    @Query("SELECT * FROM userpagesentities WHERE id = :id ORDER BY currentPage DESC LIMIT 1")
    fun getUserPageKey(id: String): Flow<UserPagesKey>

    @Query("DELETE FROM userpagesentities")
    suspend fun clearUserPageKey()
}