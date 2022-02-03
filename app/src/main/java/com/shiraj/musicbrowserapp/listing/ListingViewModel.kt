package com.shiraj.musicbrowserapp.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiraj.domain.model.Album
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

    private val _album = MutableLiveData<Output<Album>>()
    val album: LiveData<Output<Album>> = _album

    init {
        fetchAlbum()
    }

    /**
     * Method to fetch the album data.
     */
    private fun fetchAlbum() {
        viewModelScope.launch {
            albumUseCase.execute().collect {
                _album.value = it
            }
        }
    }

}