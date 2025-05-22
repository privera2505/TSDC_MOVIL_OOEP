package com.vinilo.ui.collectors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinilo.domain.model.Comment
import com.vinilo.view.R

class CollectorCommentsAdapter(
    private val comments: List<Comment>
): RecyclerView.Adapter<CollectorCommentsAdapter.CollectorCommentsAdapterHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorCommentsAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CollectorCommentsAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorCommentsAdapterHolder, position: Int) {
        val collectorComment = comments[position]
        holder.collectorCommentDescription.text = collectorComment.description
        //holder.collectorCommentRating.text = collectorComment.rating.toString()

        // Limpiar estrellas previas
        holder.commentRatingStars.removeAllViews()

        // Agregar estrellas según el rating
        repeat(collectorComment.rating.toInt()) {
            val star = ImageView(holder.itemView.context).apply {
                setImageResource(R.drawable.star)
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    width = 32
                    height = 32
                    marginEnd = 4
                }
            }
            holder.commentRatingStars.addView(star)
        }
    }

    override fun getItemCount(): Int = comments.size

    class CollectorCommentsAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        val collectorCommentDescription: TextView = view.findViewById(R.id.descriptionComment)
        //val collectorCommentRating: TextView = view.findViewById(R.id.rating)
        val commentRatingStars: LinearLayout = view.findViewById(R.id.rating)
    }
}