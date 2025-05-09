package com.vinilo.ui.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.model.Performer
import com.vinilo.view.R

class PerformerAdapter (
    private val performers: List<Performer>,
    private val onPerformerClick: (Performer) -> Unit
) : RecyclerView.Adapter<PerformerAdapter.PerformerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_performer,parent,false )
        return PerformerViewHolder(view)
    }


    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        val perfomer = performers[position]
        holder.performerName.text = perfomer.name
        Glide.with(holder.itemView.context)
            .load(perfomer.image.trim())
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_artists)
            .into(holder.performerImage)

        holder.itemView.setOnClickListener {
            onPerformerClick(perfomer)
        }
    }

    override fun getItemCount(): Int {
        return performers.size
    }


    class PerformerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val performerName: TextView = view.findViewById(R.id.performerName)
        val performerImage: ImageView = view.findViewById(R.id.perfomerImage)
    }

}