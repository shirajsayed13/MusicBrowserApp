package com.shiraj.musicbrowserapp.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _album = MutableLiveData<Output<List<GenreViewItem>>>()
    val album: LiveData<Output<List<GenreViewItem>>> = _album

    internal lateinit var genreViewItems: List<GenreViewItem>

    private var newInfoList: MutableList<GenreViewItem> = mutableListOf()
    private var newAlbumList: MutableSet<GenreViewItem.AlbumView> = mutableSetOf()

    init {
        fetchAlbum()
    }

    /**
     * Method to fetch the album data.
     */
    fun fetchAlbum() {
        viewModelScope.launch {
            albumUseCase.execute().collect {
                _album.value = it
            }
        }
    }

    internal fun getSearchData(newText: String?): List<GenreViewItem> {
        newInfoList.clear()
        newAlbumList.clear()
        newText?.let {
            if (it.length > 2) {
                genreViewItems.forEach { info ->
                    info.albumView.forEach { albumView ->
                        if (albumView.albumName.contains(newText, ignoreCase = true)) {
                            newAlbumList.add(
                                GenreViewItem.AlbumView(
                                    albumView.artistName,
                                    albumView.albumName,
                                    albumView.artworkUrl100
                                )
                            )
                            newInfoList.add(
                                GenreViewItem(
                                    info.genreName,
                                    newAlbumList.toList()
                                )
                            )
                        }
                    }
                }
            }
        }
        return newInfoList.toList()
    }
}