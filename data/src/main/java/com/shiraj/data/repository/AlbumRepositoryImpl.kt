package com.shiraj.data.repository

import com.shiraj.data.remote.AlbumRemoteDataSource
import com.shiraj.domain.model.Album
import com.shiraj.domain.model.GenreViewItem
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
    override suspend fun fetchAlbum(): Flow<Output<List<GenreViewItem>>?> {
        return flow {
            emit(Output.loading())
            val result = albumRemoteDataSource.fetchAlbums()
            emit(result.data?.feed?.let { parseAlbumData(it) })
        }.flowOn(Dispatchers.IO)
    }


    private fun parseAlbumData(feed: Album.Feed): Output<List<GenreViewItem>> {
        val map = mutableMapOf<String, MutableList<GenreViewItem.AlbumView>>()
        lateinit var albumView: MutableList<GenreViewItem.AlbumView>
        feed.results.forEach { result ->
            result.genres.forEach { genre ->
                if (map.containsKey(genre.name)) {
                    for ((key, value) in map) {
                        if (genre.name == key) {
                            value.add(
                                GenreViewItem.AlbumView(
                                    result.artistName,
                                    result.name,
                                    result.artworkUrl100
                                )
                            )
                        }
                    }

                } else {
                    albumView = mutableListOf()
                    albumView.add(
                        GenreViewItem.AlbumView(
                            result.artistName,
                            result.name,
                            result.artworkUrl100
                        )
                    )
                    map[genre.name] = albumView
                }
            }
        }
        val genreViewItem = mutableListOf<GenreViewItem>()
        map.forEach { updateMap ->
            genreViewItem.add(GenreViewItem(updateMap.key, updateMap.value))
        }
        return Output.success(genreViewItem.toList())
    }
}