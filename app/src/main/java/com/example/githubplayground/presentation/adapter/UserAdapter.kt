package com.example.githubplayground.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubplayground.databinding.ItemUserListBinding
import com.example.githubplayground.domain.model.User

/**
 * Created on : 01/06/21 | 00.02
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    val listUser = ArrayList<User>()
    var onLoadMore: (() -> Unit)? = null

    fun setData(list: List<User>) {
        if (list == null) return
        listUser.clear()
        listUser.addAll(list)
        notifyDataSetChanged()
    }

    fun updateData(list: List<User>) {
        val insertIndex = listUser.size - 1
        listUser.addAll(insertIndex, list)
        notifyItemRangeInserted(insertIndex, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentData = listUser[position]
        holder.bind(currentData)
        if ((position >= getItemCount() - 1))
            onLoadMore?.invoke()
    }

    override fun getItemCount() = listUser.size

    class UserViewHolder(val binding: ItemUserListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {
            binding.user = data
        }
    }
}