package com.vinilo.ui.awards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinilo.domain.model.PerformerPrizeResponse
import com.vinilo.view.R

class AwardsAdapter(
    private val prizes: List<PerformerPrizeResponse>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<AwardsAdapter.AwardsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AwardsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prize, parent, false)
        return AwardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AwardsViewHolder, position: Int) {
        val award = prizes[position]
        holder.prizeName.text = award.name
        Glide.with(holder.itemView.context)
            .load(R.drawable.award_selected_ico)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.award_selected_ico)
            .into(holder.prizeImage)

        holder.itemView.setOnClickListener {
            onItemClick(award.id)
        }
    }

    override fun getItemCount(): Int = prizes.size

    class AwardsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val prizeName: TextView = view.findViewById(R.id.namePrize)
        val prizeImage: ImageView = view.findViewById(R.id.imagePrize)
    }
}
