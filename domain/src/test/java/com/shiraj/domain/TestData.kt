package com.shiraj.domain

import com.shiraj.domain.model.Album


fun getDummyAlbum() =
    Album(
        Album.Feed(
            mutableListOf(
                Album.Feed.Result(
                    "62763238",
                    "Yo Gotti",
                    "https://music.apple.com/us/artist/yo-gotti/62763238",
                    "https://is4-ssl.mzstatic.com/image/thumb/Music116/v4/99/33/26/99332631-6f1b-9bcb-101e-5db6e4eea8ba/886449863587.jpg/100x100bb.jpg",
                    mutableListOf(
                        Album.Feed.Result.Genre(
                            "34",
                            "Music",
                            "https://itunes.apple.com/us/genre/id34"
                        )
                    ),
                    "1607584637",
                    "albums",
                    "CM10: Free Game (Side B)",
                    "2022-02-04",
                    "https://music.apple.com/us/album/cm10-free-game-side-b/1607584637"
                )
            )
        )
    )