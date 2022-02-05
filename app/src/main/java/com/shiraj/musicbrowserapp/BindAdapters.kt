package com.shiraj.musicbrowserapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl", "isSmallerImage", requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, isSmallerImage: Boolean) {
    val builder = Picasso.with(imageView.context)
        .load(url)
        .error(R.drawable.ic_image_error)
    if (isSmallerImage) {
        builder.resize(256, 256)
        builder.placeholder(R.drawable.ic_music_player)
        builder.centerCrop()
    }
    builder.into(imageView)
}