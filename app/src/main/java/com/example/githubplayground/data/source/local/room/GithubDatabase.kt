package com.example.githubplayground.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubplayground.data.source.local.entity.UserPagesKey

/**
 * Created on : 31/05/21 | 23.06
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@Database(
    entities = [UserPagesKey::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun userPagesKeyDao(): UserPagesKeyDao
}