package com.shiraj.data.repository

import com.shiraj.data.remote.AlbumRemoteDataSource
import com.shiraj.domain.model.Album
import com.shiraj.domain.model.Output
import com.shiraj.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementation of AlbumRepository
 * @param albumRemoteDataSource the object of remote data source
 */
internal class AlbumRepositoryImpl @Inject constructor(
    private val albumRemoteDataSource: AlbumRemoteDataSource
) : AlbumRepository {
    override suspend fun fetchAlbum(): Flow<Output<Album>> {
        return flow {
            emit(Output.loading())
            val result = albumRemoteDataSource.fetchAlbums()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}