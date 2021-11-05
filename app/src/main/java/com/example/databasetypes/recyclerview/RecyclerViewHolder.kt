package com.example.databasetypes.recyclerview

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databasetypes.R

class RecyclerViewHolder(
    private val itemView: View,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    var bookmarkImageBtn: ImageButton = itemView.findViewById(R.id.bookmarkImageBtn)
    val movieImageView: ImageView = itemView.findViewById(R.id.movieImageView)
    val movieNameTV: TextView = itemView.findViewById(R.id.movieNameTV)
    val rating1TV: TextView = itemView.findViewById(R.id.rating1TV)
    val rating2TV: TextView = itemView.findViewById(R.id.rating2TV)
    val ratingMetascoreTV: TextView = itemView.findViewById(R.id.ratingMetascoreTV)

    init {
        itemView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

}