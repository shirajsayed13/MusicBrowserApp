package com.shiraj.musicbrowserapp.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shiraj.domain.model.Output
import com.shiraj.musicbrowserapp.R
import com.shiraj.musicbrowserapp.base.BaseFragment
import com.shiraj.musicbrowserapp.databinding.FragmentListingBinding

class ListingFragment : BaseFragment() {

    private val listingViewModel: ListingViewModel by viewModels()
    private lateinit var binding: FragmentListingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentListingBinding.inflate(inflater, container, false)
            with(binding) {
                headerTitle = this@ListingFragment.getString(R.string.listing_title)
                root
            }
        }
    }


    override fun subscribeUI() {
        listingViewModel.album.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Output.Status.SUCCESS -> {
                    result.data?.let {
                        println(it)
                    }
                }
                Output.Status.ERROR -> {
                    result.message?.let {
                        showError(it) {
                            println()
                        }
                    }
                }
                Output.Status.LOADING -> {

                }
            }
        }
    }
}