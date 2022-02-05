package com.shiraj.musicbrowserapp.listing

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.shiraj.domain.model.Output
import com.shiraj.musicbrowserapp.MainActivity
import com.shiraj.musicbrowserapp.R
import com.shiraj.musicbrowserapp.base.BaseFragment
import com.shiraj.musicbrowserapp.databinding.FragmentListingBinding

class ListingFragment : BaseFragment(), SearchView.OnQueryTextListener {

    private val listingViewModel: ListingViewModel by viewModels()
    private lateinit var binding: FragmentListingBinding
    private lateinit var albumListingAdapter: AlbumListingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentListingBinding.inflate(inflater, container, false)
            setHasOptionsMenu(true)
            with(binding) {
                root
            }
        }
    }


    override fun subscribeUI() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.listing_title)
        albumListingAdapter = AlbumListingAdapter()
        binding.apply {
            rvGenre.adapter = albumListingAdapter
        }
        listingViewModel.album.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Output.Status.SUCCESS -> {
                    result.data?.let { list ->
                        binding.loading.hide()
                        listingViewModel.genreViewItems = list
                        albumListingAdapter.list = list
                    }
                }

                Output.Status.ERROR -> {
                    result.message?.let {
                        showError(it) {
                            listingViewModel.fetchAlbum()
                        }
                    }
                }

                Output.Status.LOADING -> {
                    binding.loading.show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.listing_menu, menu)
        val searchItem = menu.findItem(R.id.search)

        searchItem?.let {
            val searchView = it.actionView as SearchView
            searchView.queryHint = "Search Album"
            searchView.setOnQueryTextListener(this)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        albumListingAdapter.list = listingViewModel.getSearchData(newText)
        newText?.let { query ->
            if (query.length < 3)
                albumListingAdapter.list = listingViewModel.genreViewItems
        }
        return true
    }
}