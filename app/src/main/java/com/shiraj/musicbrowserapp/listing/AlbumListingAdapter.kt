package com.shiraj.musicbrowserapp.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.domain.model.GenreViewItem
import com.shiraj.musicbrowserapp.databinding.ItemGenreBinding
import kotlin.properties.Delegates

/**
 * RecyclerView Adapter to display *GenreView*.
 *
 * @property list the list of GenreViewItem in this Adapter.
 * @property onAlbumClick is the item click listener.
 */
class AlbumListingAdapter : RecyclerView.Adapter<AlbumListingAdapter.AlbumListingViewHolder>() {

    var list: List<GenreViewItem> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class AlbumListingViewHolder(
        private val binding: ItemGenreBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val musicInnerListingAdapter: MusicInnerListingAdapter = MusicInnerListingAdapter()

        init {
            binding.apply {
                rvGenre.apply {
                    adapter = musicInnerListingAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }

        fun bind(item: GenreViewItem) {
            binding.item = item
            binding.position = adapterPosition
            musicInnerListingAdapter.albumViews = item.albumView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListingViewHolder =
        AlbumListingViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: AlbumListingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}