package com.shiraj.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shiraj.domain.getDummyGenreViewItem
import com.shiraj.domain.model.Output
import com.shiraj.domain.repository.AlbumRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumUseCaseImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var albumRepository: AlbumRepository

    private lateinit var albumUseCase: AlbumUseCaseImpl

    @Before
    fun setUp() {
        albumUseCase = AlbumUseCaseImpl(albumRepository)
    }

    @Test
    fun `Given Album When UseCase fetchAlbums returns Success`() = runBlocking {
        //GIVEN
        val inputFlow = flowOf(Output.success(listOf(getDummyGenreViewItem())))
        Mockito.`when`(albumRepository.fetchAlbum()).thenReturn(inputFlow)
        //WHEN
        val output = albumUseCase.execute().toList()
        //THEN
        assert(output[0]?.data?.size == inputFlow.toList().size)
    }
}