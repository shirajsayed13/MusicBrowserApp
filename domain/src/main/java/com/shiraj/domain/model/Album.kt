package com.shiraj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val feed: Feed
) : Parcelable {

    @Parcelize
    data class Feed(
        val results: List<Result>,
    ) : Parcelable {

        @Parcelize
        data class Result(
            val artistId: String,
            val artistName: String,
            val artistUrl: String,
            val artworkUrl100: String,
            val genres: List<Genre>,
            val id: String,
            val kind: String,
            val name: String,
            val releaseDate: String,
            val url: String
        ) : Parcelable {

            @Parcelize
            data class Genre(
                val genreId: String?,
                val name: String,
                val url: String
            ) : Parcelable
        }
    }
}