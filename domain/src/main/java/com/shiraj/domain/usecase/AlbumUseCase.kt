package com.shiraj.domain.usecase

import com.shiraj.domain.model.Output
import com.shiraj.domain.model.Album
import com.shiraj.domain.model.GenreViewItem
import kotlinx.coroutines.flow.Flow

/**
 * Interface of Album UseCase to expose to ui module
 */
interface AlbumUseCase {

    /**
     * UseCase Method to fetch the Album from Data Layer
     */
    suspend fun execute(): Flow<Output<List<GenreViewItem>>?>
}