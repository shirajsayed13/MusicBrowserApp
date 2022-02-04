package com.shiraj.musicbrowserapp

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("show")
fun setVisibility(v: View, show: Boolean) {
    v.visibility = if (show) View.VISIBLE else View.GONE
}

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