package com.shiraj.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shiraj.data.getDummyAlbum
import com.shiraj.data.remote.AlbumRemoteDataSource
import com.shiraj.domain.model.Output
import com.shiraj.domain.repository.AlbumRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var albumRepository: AlbumRepository

    @Mock
    lateinit var  albumRemoteDataSource: AlbumRemoteDataSource

    @Before
    fun setUp() {
        albumRepository = AlbumRepositoryImpl(albumRemoteDataSource)
    }

    @Test
    fun `Given Album When fetchAlbum returns Success`() = runBlocking {
        //GIVEN
        val givenAlbum = getDummyAlbum()
        val givenAlbumOutput = Output.success(givenAlbum)
        val inputFlow = listOf(Output.loading(), Output.success(true))
        `when`(albumRemoteDataSource.fetchAlbums()).thenReturn(givenAlbumOutput)

        //WHEN
        val outputFlow = albumRepository.fetchAlbum().toList()

        //THEN
        assert(outputFlow.size == inputFlow.size)
    }
}