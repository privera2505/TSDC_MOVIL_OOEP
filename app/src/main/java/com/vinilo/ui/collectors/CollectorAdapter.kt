package com.vinilo.ui.collectors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.model.Collector
import com.vinilo.view.R

class CollectorAdapter (
    private val collectors: List<Collector>
) : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collector,parent,false)
        return CollectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val collector = collectors[position]
        holder.collectorName.text = collector.name
        Glide.with(holder.itemView.context)
            .load(R.drawable.ic_user)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_user)
            .into(holder.collectorImage)
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val collectorName: TextView = view.findViewById(R.id.nameCollector)
        val collectorImage: ImageView = view.findViewById(R.id.imageCollector)
    }
}