package com.shiraj.musicbrowserapp.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiraj.domain.model.Album
import com.shiraj.domain.model.GenreViewItem
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
        val map = mutableMapOf<String, List<GenreViewItem>>()
        val genreViewItemList = mutableListOf<GenreViewItem>()
        val genreViewItem = mutableSetOf<GenreViewItem>()
        val albumView = mutableListOf<GenreViewItem.AlbumView>()
        feed.results.map { genreResult ->
            genreResult.genres.map { genre ->
                if (map.containsKey(genre.name)) {
                    albumView.add(
                        GenreViewItem.AlbumView(
                            genreResult.artistName,
                            genreResult.name,
                        )
                    )
                    genreViewItemList.add(
                        GenreViewItem(
                            genre.name,
                            albumView
                        )
                    )
                    map.plus(genre.name to genreViewItemList)
                } else {
                    albumView.add(
                        GenreViewItem.AlbumView(
                            genreResult.artistName,
                            genreResult.name,
                        )
                    )
                    genreViewItemList.add(
                        GenreViewItem(
                            genre.name,
                            albumView
                        )
                    )
                    map.put(genre.name, genreViewItemList)
                }
            }
        }
        println("CHECK THE MAP value $map")
        return genreViewItemList
    }

    private fun albumView(genreResult: GenreViewItem.AlbumView) {
        GenreViewItem.AlbumView(
            genreResult.artistName,
            genreResult.albumName,
        )
    }

}