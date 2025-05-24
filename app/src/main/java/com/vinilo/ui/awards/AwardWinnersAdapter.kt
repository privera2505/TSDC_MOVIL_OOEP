package com.vinilo.ui.awards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.domain.model.PerformerWinner
import com.vinilo.view.R

class AwardWinnersAdapter(
    private val winners: List<PerformerWinner>
) : RecyclerView.Adapter<AwardWinnersAdapter.WinnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinnerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_award_winner, parent, false)
        return WinnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: WinnerViewHolder, position: Int) {
        val winner = winners[position]
        holder.name.text = winner.performer.name
        Glide.with(holder.itemView.context)
            .load(winner.performer.image)
            .circleCrop()
            .placeholder(R.drawable.ic_user)
            .into(holder.image)
    }

    override fun getItemCount(): Int = winners.size

    class WinnerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.winnerName)
        val image: ImageView = view.findViewById(R.id.winnerImage)
    }
}
