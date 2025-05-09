package com.vinilo.ui.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.model.Album
import com.vinilo.view.R
import java.text.SimpleDateFormat

class AlbumsAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist_detail_album, parent, false)
        return AlbumsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = albums[position]
        holder.albumName.text = album.name ?: ""

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        holder.albumReleaseDate.text = sdf.format(album.releaseDate) ?: ""
        holder.albumGenre.text = album.genre?.toString() ?: ""
        holder.albumRecordLabel.text = album.recordLabel?.toString() ?: ""
        Glide.with(holder.itemView.context)
            .load(album.cover)
            .centerCrop()
            .into(holder.albumCover)
    }

    override fun getItemCount(): Int = albums.size

    class AlbumsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumCover: ImageView = view.findViewById(R.id.ivAlbumCover)
        val albumName: TextView = view.findViewById(R.id.tvAlbumName)
        val albumReleaseDate: TextView = view.findViewById(R.id.tvReleaseDate)
        val albumGenre: TextView = view.findViewById(R.id.tvGenre)
        val albumRecordLabel: TextView = view.findViewById(R.id.tvRecordLabel)
    }
}
