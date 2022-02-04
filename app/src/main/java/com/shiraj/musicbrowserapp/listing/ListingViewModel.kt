package com.shiraj.musicbrowserapp.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiraj.domain.model.Album
import com.shiraj.domain.model.GenreViewItem
import com.shiraj.domain.model.Output
import com.shiraj.domain.usecase.AlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val albumUseCase: AlbumUseCase
) : ViewModel() {

    private val _album = MutableLiveData<List<GenreViewItem>>()
    val album: LiveData<List<GenreViewItem>> = _album

    init {
        fetchAlbum()
    }

    /**
     * Method to fetch the album data.
     */
    private fun fetchAlbum() {
        viewModelScope.launch {
            albumUseCase.execute().collect {
                _album.value = it.data?.let { it1 -> parseAlbumData(it1.feed) }
            }
        }
    }


    private fun parseAlbumData(feed: Album.Feed): List<GenreViewItem> {
        val genreViewItem = mutableSetOf<GenreViewItem>()
        val albumView = mutableListOf<GenreViewItem.AlbumView>()
        feed.results.map { genreResult ->
            albumView.add(
                GenreViewItem.AlbumView(
                    genreResult.artistName,
                    genreResult.name,
                    GenreViewItem.AlbumView.AlbumDetailView(
                        genreResult.artistUrl
                    ),
                    genreResult.artworkUrl100
                )
            )
            genreResult.genres.map { genre ->
                genreViewItem.add(
                    GenreViewItem(
                        genre.name,
                        genre.genreId,
                        albumView
                    )
                )
            }
        }
        return genreViewItem.toList()

    }

}