package com.vinilo.ui.collectors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinilo.domain.model.CollectorAlbum
import com.vinilo.view.R


class CollectorAlbumAdapter(
    private val albums: List<CollectorAlbum>
) : RecyclerView.Adapter<CollectorAlbumAdapter.CollectorAlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorAlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_track, parent, false)
        return CollectorAlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorAlbumViewHolder, position: Int) {
        val collectorAlbum = albums[position]
        holder.collectorAlbumItemName.text = collectorAlbum.status
    }

    override fun getItemCount(): Int = albums.size

    class CollectorAlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val collectorAlbumItemName: TextView = view.findViewById(R.id.nameTrack)
    }
}
