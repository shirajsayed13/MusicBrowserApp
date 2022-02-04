package com.shiraj.musicbrowserapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.shiraj.musicbrowserapp.base.BaseFragment
import com.shiraj.musicbrowserapp.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding

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
                sharedElementEnterTransition =
                    TransitionInflater.from(context).inflateTransition(android.R.transition.move)
                root
            }
        }
    }

    override fun subscribeUI() {
        TODO("Not yet implemented")
    }

    companion object {
        const val ITEM = "AlbumViewItem"
    }
}