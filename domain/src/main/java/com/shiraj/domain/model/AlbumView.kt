package com.shiraj.domain.model

data class GenreViewItem(
    val genreName: String,
    val albumView: List<AlbumView>
) {
    data class AlbumView(
        val artistName: String,
        val albumName: String,
    )
}