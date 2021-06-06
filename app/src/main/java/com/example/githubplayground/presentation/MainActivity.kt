package com.example.githubplayground.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubplayground.data.Resource
import com.example.githubplayground.databinding.ActivityMainBinding
import com.example.githubplayground.presentation.adapter.UserAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: UserAdapter
    private val viewModel: MainViewModel by viewModel()
    private var totalPages = 10
    private var pageToLoad = 1
    private var currentPageLoaded = 1
    private var firstSearch = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRv()

        binding.btnSearch.setOnClickListener {
            firstSearch = true
            viewModel.clearPageData()
            search(getQuery()!!, pageToLoad)
        }




        textFieldListener()
        //observePage()
        loadMore()

    }

    private fun loadMore() {
        userAdapter.onLoadMore = {
            pageToLoad += 1
            search(getQuery()!!, pageToLoad)
        }
    }

    private fun search(query: String, page: Int) {
        isLoading(true)
        viewModel.searchUser(query, page).observe(this, Observer { response ->
            when (response) {
                is Resource.Loading -> isLoading(true)
                is Resource.Error -> {
                    isLoading(false)
                    Snackbar.make(
                        this,
                        binding.root,
                        "Error = ${response.message}",
                        Snackbar.LENGTH_SHORT
                    )
                }
                is Resource.Success -> {
                    isLoading(false)
                    val result = response.data
                    result?.let { data ->
                        if (data.isEmpty()) {
                            Snackbar.make(
                                this,
                                binding.root,
                                "User Not found...",
                                Snackbar.LENGTH_SHORT
                            )
                        } else {
                            if (page == 1) userAdapter.setData(data) else userAdapter.updateData(
                                data
                            )
                        }
                    }

                }
            }
        })
    }

//    private fun observePage() {
//        //need to fix
//        if (firstSearch){
//            viewModel.lastestPage().observe(this, Observer { paging ->
//                paging?.let {
//                    pageToLoad = it.currentPage + 1
//                    totalPages = it.totalCount / 20
//                    currentPageLoaded = it.currentPage
//                }
//            })
//        }
//
//
//
//    }

    fun textFieldListener(){
        binding.outlinedTextFieldSearch.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                userAdapter.clearData()
                pageToLoad = 1
            }

        })
    }

    private fun isLoading(state: Boolean) {
        binding.loading = state
    }

    private fun getQuery(): String {
        val query = binding.outlinedTextFieldSearch.editText?.text
        return query.toString()
    }

    private fun initRv() = binding.rvUser.apply {
        userAdapter = UserAdapter()
        layoutManager = LinearLayoutManager(this@MainActivity)
        val divider = DividerItemDecoration(
            this@MainActivity,
            (layoutManager as LinearLayoutManager).orientation
        )
        addItemDecoration(divider)
        adapter = userAdapter
    }
}