package com.example.githubplayground.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubplayground.R
import de.hdodenhof.circleimageview.CircleImageView
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

/**
 * Created on : 01/06/21 | 00.37
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

@OptIn(KoinApiExtension::class)
object BindingAdapters : KoinComponent {
    @BindingAdapter("glideSrc")
    @JvmStatic
    fun setGlideImage(view: CircleImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(view.context)
                .load(it)
                .fitCenter()
                .apply(RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_image)
                .into(view)
        }
    }
}