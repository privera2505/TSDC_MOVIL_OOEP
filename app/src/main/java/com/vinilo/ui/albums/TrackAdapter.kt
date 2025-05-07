package com.vinilo.ui.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.domain.model.Track
import com.vinilo.view.R

class TrackAdapter(private val tracks: List<Track>) :
    RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackName.text = track.name
        holder.trackDuration.text = track.duration
        Glide.with(holder.itemView.context)
            .load(R.drawable.album_cover)
            .centerCrop()
            .into(holder.trackCover)
    }

    override fun getItemCount(): Int = tracks.size

    class TrackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trackName: TextView = view.findViewById(R.id.nameTrack)
        val trackDuration: TextView = view.findViewById(R.id.trackDuration)
        val trackCover: ImageView = view.findViewById(R.id.imageAlbumCoverTrack)
    }
}
