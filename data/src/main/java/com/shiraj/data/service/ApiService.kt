package com.shiraj.data.service

import com.shiraj.domain.model.Album
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API Service
 */
interface ApiService {

    @GET("api/v2/us/music/most-played/50/albums.json")
    suspend fun getAlbums(): Response<Album>

}