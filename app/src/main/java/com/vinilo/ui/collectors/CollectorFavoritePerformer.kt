package com.vinilo.ui.collectors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.domain.model.Performer
import com.vinilo.view.R

class CollectorFavoritePerformer(
    private val favorites: List<Performer>
): RecyclerView.Adapter<CollectorFavoritePerformer.CollectorFavoritePerformerAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorFavoritePerformerAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_performer, parent, false)
        return CollectorFavoritePerformerAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorFavoritePerformerAdapterHolder, position: Int) {
        val collectorFavorites = favorites[position]
        holder.nameFavoritePerformer.text = collectorFavorites.name
        holder.descriptionFavoritePerformer.text = collectorFavorites.description

        Glide.with(holder.itemView.context)
            .load(collectorFavorites.image )
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_artists)
            .into(holder.coverPerformerFavorite)

    }

    override fun getItemCount(): Int = favorites.size

    class CollectorFavoritePerformerAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameFavoritePerformer: TextView = view.findViewById(R.id.nameFavorite)
        val descriptionFavoritePerformer: TextView = view.findViewById(R.id.descriptionFavorite)
        val coverPerformerFavorite: ImageView = view.findViewById(R.id.imageFavoritePerformer)
    }
}