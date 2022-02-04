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

    private val _album = MutableLiveData<Output<List<GenreViewItem>>>()
    val album: LiveData<Output<List<GenreViewItem>>> = _album

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



}