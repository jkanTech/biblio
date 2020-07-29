package com.jkantech.biblioispt.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jkantech.biblioispt.R

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.default_book_cover)
        .circleCrop()
       // .error(R.mipmap.ic_launcher_round)
        .error(R.drawable.default_book_cover)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}