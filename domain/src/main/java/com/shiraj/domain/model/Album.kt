package com.shiraj.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val feed: Feed
) : Parcelable {

    @Parcelize
    data class Feed(
        val author: Author,
        val copyright: String,
        val country: String,
        val icon: String,
        val id: String,
        val links: List<Link>,
        val results: List<Result>,
        val title: String,
        val updated: String
    ) : Parcelable {

        @Parcelize
        data class Author(
            val name: String,
            val url: String
        ) : Parcelable

        @Parcelize
        data class Link(
            val self: String
        ) : Parcelable

        @Parcelize
        data class Result(
            val artistId: String,
            val artistName: String,
            val artistUrl: String,
            val artworkUrl100: String,
            val contentAdvisoryRating: String,
            val genres: List<Genre>,
            val id: String,
            val kind: String,
            val name: String,
            val releaseDate: String,
            val url: String
        ) : Parcelable {

            @Parcelize
            data class Genre(
                val genreId: String,
                val name: String,
                val url: String
            ) : Parcelable
        }
    }
}