package com.example.githubplayground.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubplayground.data.source.local.entity.UserPagesKeyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created on : 31/05/21 | 23.07
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */
@Dao
interface UserPagesKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserPageKeys(moviePagesKey: List<UserPagesKeyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserPageKeys(moviePagesKey: UserPagesKeyEntity)

    @Query("SELECT * FROM userpagesentities ORDER BY updatedAt DESC LIMIT 1")
    fun getUserPageKey(): Flow<UserPagesKeyEntity>

    @Query("DELETE FROM userpagesentities")
    suspend fun clearUserPageKey()
}