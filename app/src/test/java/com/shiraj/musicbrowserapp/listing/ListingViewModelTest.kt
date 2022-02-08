package com.shiraj.musicbrowserapp.listing

import com.shiraj.domain.model.Output
import com.shiraj.domain.usecase.AlbumUseCase
import com.shiraj.musicbrowserapp.BaseViewModelTest
import com.shiraj.musicbrowserapp.getDummyGenreViewItem
import com.shiraj.musicbrowserapp.runBlockingMainTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListingViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var albumUseCase: AlbumUseCase

    private lateinit var listingViewModel: ListingViewModel

    @Before
    fun setUp() {
        listingViewModel = ListingViewModel(albumUseCase)
    }

    @Test
    fun `Given Album When fetchAlbums should return Success`() = runBlockingMainTest {
        //GIVEN
        val genreViewItems = flowOf(Output.success(listOf(getDummyGenreViewItem())))

        //WHEN
        Mockito.doReturn(genreViewItems).`when`(albumUseCase).execute()
        listingViewModel.fetchAlbum()

        //THEN
        assertEquals(1, listingViewModel.album.value?.data?.size)
    }

}