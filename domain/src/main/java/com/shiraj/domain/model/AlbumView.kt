package com.shiraj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreViewItem(
    val genreName: String,
    val albumView: List<AlbumView>
) : Parcelable {

    @Parcelize
    data class AlbumView(
        val artistName: String,
        val albumName: String,
        val artworkUrl100: String
    ) : Parcelable
}