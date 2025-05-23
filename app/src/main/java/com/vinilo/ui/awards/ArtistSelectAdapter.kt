package com.vinilo.ui.awards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.domain.model.Performer
import com.vinilo.view.R

class ArtistSelectAdapter(
    private val artists: List<Performer>,
    private val onItemClick: (Performer) -> Unit
) : RecyclerView.Adapter<ArtistSelectAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artistName: TextView = view.findViewById(R.id.artistName)
        val artistImage: ImageView = view.findViewById(R.id.artistImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist_select, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.artistName.text = artist.name
        Glide.with(holder.itemView.context)
            .load(artist.image)
            .into(holder.artistImage)

        holder.itemView.setOnClickListener { onItemClick(artist) }
    }

    override fun getItemCount(): Int = artists.size
}
