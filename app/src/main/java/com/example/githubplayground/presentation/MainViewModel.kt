package com.example.githubplayground.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubplayground.domain.usecase.Usecase

/**
 * Created on : 31/05/21 | 23.31
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MainViewModel(val usecase: Usecase) : ViewModel() {
    fun searchUser(query: String, page: Int) = usecase.doSearchUser(query, page)

    fun lastestPage(query: String) = usecase.doGetCurrentPage(query).asLiveData()
}