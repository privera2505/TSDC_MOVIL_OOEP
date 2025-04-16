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

class AlbumAdapter(private val albums: List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    // Crear el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    // Asignar los datos a cada ítem
    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.albumTitle.text = album.name
        // Aquí deberías usar una librería como Glide o Picasso si las imágenes vienen de una URL
        Glide.with(holder.itemView.context)
            .load(album.cover)
            .placeholder(R.drawable.ic_launcher_background) // mientras carga
            .error(R.drawable.ic_launcher_foreground) // si hay error
            .into(holder.albumImage)
    }

    // Retornar la cantidad de elementos
    override fun getItemCount(): Int {
        return albums.size
    }

    // ViewHolder para guardar las vistas de un ítem
    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumTitle: TextView = view.findViewById(R.id.albumName)
        val albumImage: ImageView = view.findViewById(R.id.albumImage)
    }
}
