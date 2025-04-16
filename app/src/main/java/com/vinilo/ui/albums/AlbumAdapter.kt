package com.vinilo.ui.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.model.Album
import com.vinilo.view.R

class AlbumAdapter(
    private val albums: List<Album>,
    private val onAlbumClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.albumTitle.text = album.name
        Glide.with(holder.itemView.context)
            .load(album.cover)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_artists)
            .into(holder.albumImage)

        holder.itemView.setOnClickListener {
            onAlbumClick(album)
        }
    }



    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumTitle: TextView = view.findViewById(R.id.albumName)
        val albumImage: ImageView = view.findViewById(R.id.albumImage)
    }
}
