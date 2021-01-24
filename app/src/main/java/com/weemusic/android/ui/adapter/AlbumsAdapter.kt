package com.weemusic.android.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.domain.Album
import com.weemusic.android.ui.MainActivity
import org.threeten.bp.LocalDate
import javax.inject.Inject


class AlbumsAdapter @Inject constructor() : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private var albums: ArrayList<Album> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.album_view_holder, parent, false)

        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) =
        holder.onBind(albums[position])

    fun submitList(newAlbums : List<Album>) {
        this.albums.clear()
        this.albums.addAll(newAlbums)
        notifyDataSetChanged()
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(album: Album) {
            val coverUrl = album.images?.last()
            val title = album.title
            val artist = album.artist
            val price = album.price
            val releaseDate = album.releaseDate

            val albumEpoch = releaseDate!!.toEpochDay()
            val epochNow = LocalDate.now().toEpochDay()

            val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
            val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
            val tvArtist: TextView = itemView.findViewById(R.id.tvArtist)
            val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
            val tvNewAlbum: ImageView = itemView.findViewById(R.id.ivNewAlbum)

            Picasso
                .with(itemView.context)
                .load(coverUrl)
                .fit()
                .centerCrop()
                .into(ivCover)
            tvTitle.text = title
            tvArtist.text = artist
            tvPrice.text = price

            // If album's release date is less than or equal to 7 days ago, then it is new.
            tvNewAlbum.visibility = if (epochNow - albumEpoch <= 7) View.VISIBLE else View.GONE
        }
    }
}
