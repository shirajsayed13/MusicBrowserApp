package com.shiraj.domain.model

data class GenreViewItem(
    val genreName: String,
    val genreId: String,
    val albumView: List<AlbumView>
) {
    data class AlbumView(
        val artistName: String,
        val albumName: String,
        val artistUrl: AlbumDetailView,
        val artWorkUrl: String,
    ) {
        data class AlbumDetailView(
            val detailUrl: String?
        )
    }
}

