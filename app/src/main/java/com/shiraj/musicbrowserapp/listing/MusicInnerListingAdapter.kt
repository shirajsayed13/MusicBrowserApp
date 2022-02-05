package com.shiraj.musicbrowserapp.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiraj.domain.model.GenreViewItem
import com.shiraj.musicbrowserapp.databinding.ItemMusicBinding
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * RecyclerView Adapter to display *GenreView*.
 *
 * @property list the list of GenreViewItem in this Adapter.
 * @property onAlbumClick is the item click listener.
 */
class MusicInnerListingAdapter @Inject constructor() :
    RecyclerView.Adapter<MusicInnerListingAdapter.AlbumListingViewHolder>() {

    internal var onAlbumClickListener: (GenreViewItem.AlbumView, Int) -> Unit = { _, _ -> }

    internal var albumViews: List<GenreViewItem.AlbumView> by
    Delegates.observable(listOf()) { _, _, _ -> notifyDataSetChanged() }

    inner class AlbumListingViewHolder(
        private val binding: ItemMusicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GenreViewItem.AlbumView) {
            binding.item = item
            binding.position = adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListingViewHolder =
        AlbumListingViewHolder(
            ItemMusicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                onAlbumClickListener(albumViews[adapterPosition], adapterPosition)
            }
        }

    override fun onBindViewHolder(holder: AlbumListingViewHolder, position: Int) {
        holder.bind(albumViews[position])
    }

    override fun getItemCount(): Int = albumViews.size
}