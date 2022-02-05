package com.shiraj.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shiraj.data.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumRemoteDataSourceTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var retrofit: Retrofit

    @Mock
    lateinit var apiService: ApiService

    private lateinit var albumRemoteDataSource: AlbumRemoteDataSource

    @Before
    fun setUp() {
        albumRemoteDataSource = AlbumRemoteDataSource(apiService, retrofit)
    }

    @Test
    fun `Given Album When fetchAlbumData returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getAlbums())
            .thenThrow(mockitoException)
        //WHEN
        val fetchAlbum = albumRemoteDataSource.fetchAlbums()
        //THEN
        assert(fetchAlbum.message == "Unknown Error")
    }

    @Test
    fun `Given Album When fetchAlbumData returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(apiService.getAlbums())
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchAlbum = albumRemoteDataSource.fetchAlbums()
        //THEN
        assert(fetchAlbum.message == "Unknown Error")
    }
}