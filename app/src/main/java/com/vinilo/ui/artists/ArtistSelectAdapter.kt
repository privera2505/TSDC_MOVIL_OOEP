package com.vinilo.ui.artists

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
    private val onClick: (Performer) -> Unit
) : RecyclerView.Adapter<ArtistSelectAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist_select, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.name.text = artist.name
        Glide.with(holder.itemView.context)
            .load(artist.image)
            .into(holder.image)
        holder.itemView.setOnClickListener { onClick(artist) }
    }

    override fun getItemCount(): Int = artists.size

    class ArtistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.artistName)
        val image: ImageView = view.findViewById(R.id.artistImage)
    }
}
