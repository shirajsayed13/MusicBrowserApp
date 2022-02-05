package com.shiraj.musicbrowserapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.shiraj.domain.model.GenreViewItem
import com.shiraj.musicbrowserapp.base.BaseFragment
import com.shiraj.musicbrowserapp.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    private val albumViewItem by lazy { arguments?.getParcelable<GenreViewItem.AlbumView>(ITEM) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (::binding.isInitialized) {
            binding.root
        } else {
            binding = FragmentDetailBinding.inflate(inflater, container, false)
            with(binding) {
                lifecycleOwner = this@DetailFragment
                item = albumViewItem
                sharedElementEnterTransition =
                    TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                root
            }
        }
    }

    override fun subscribeUI() {

    }

    companion object {
        const val ITEM = "AlbumViewItem"
    }
}