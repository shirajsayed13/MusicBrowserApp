package com.shiraj.data.remote

import com.shiraj.data.BaseRemoteDataSource
import com.shiraj.data.service.ApiService
import com.shiraj.domain.model.Output
import com.shiraj.domain.model.Album
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * RemoteDataSource of Album API service
 */
class AlbumRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {

    suspend fun fetchAlbums(): Output<Album> {
        return getResponse(
            request = { apiService.getAlbums() },
            defaultErrorMessage = "Error fetching albums"
        )
    }
}