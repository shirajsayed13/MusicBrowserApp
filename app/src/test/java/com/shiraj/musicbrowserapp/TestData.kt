package com.shiraj.musicbrowserapp

import com.shiraj.domain.model.GenreViewItem

fun getDummyGenreViewItem() =
    GenreViewItem(
        "Music",
        listOf(
            GenreViewItem.AlbumView(
                "Yo Gotti",
                "CM10: Free Game (Side B)",
                "https://is4-ssl.mzstatic.com/image/thumb/Music116/v4/99/33/26/99332631-6f1b-9bcb-101e-5db6e4eea8ba/886449863587.jpg/100x100bb.jpg",
            )
        )
    )