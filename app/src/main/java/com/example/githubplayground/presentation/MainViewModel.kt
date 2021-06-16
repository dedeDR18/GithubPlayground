package com.example.githubplayground.presentation

import androidx.lifecycle.*
import com.example.githubplayground.domain.usecase.Usecase
import kotlinx.coroutines.launch

/**
 * Created on : 31/05/21 | 23.31
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MainViewModel(private val usecase: Usecase) : ViewModel() {
    fun searchUser(query: String, page: Int) = usecase.doSearchUser(query, page).asLiveData()


    fun clearPageData() {
        usecase.clearPageData(viewModelScope)
    }

    fun getTotalCount(): LiveData<Int> {
        val total = MutableLiveData<Int>()
        viewModelScope.launch {
            total.postValue(usecase.getCurrentTotalCount())
        }
       return total
    }


}