package com.shiraj.domain.usecase

import com.shiraj.domain.model.Output
import com.shiraj.domain.model.Album
import com.shiraj.domain.model.GenreViewItem
import com.shiraj.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of Album UseCase
 */
internal class AlbumUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : AlbumUseCase {

    override suspend fun execute(): Flow<Output<List<GenreViewItem>>?> {
        return albumRepository.fetchAlbum()
    }
}