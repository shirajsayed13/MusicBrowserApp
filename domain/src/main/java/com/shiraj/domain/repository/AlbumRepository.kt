package com.shiraj.domain.repository

import com.shiraj.domain.model.Output
import com.shiraj.domain.model.Album
import kotlinx.coroutines.flow.Flow

/**
 * Interface of Album Repository to expose to other module
 */
interface AlbumRepository {

    /**
     * Method to fetch the album from Repository
     * @return Flow of Outputs with Album Response
     */
    suspend fun fetchAlbum(): Flow<Output<Album>>

}