package com.example.databasetypes.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databasetypes.R
import com.example.databasetypes.inflate
import com.example.databasetypes.roomdatabase.Movie

class RecyclerAdapter(
    private val onItemClicked: (position: Int) -> Unit

) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerViewHolder(parent.inflate(R.layout.adapter_items_list)) { position ->
            onItemClicked(position)
        }

    private var recyclerDataList = emptyList<Movie>()

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val recyclerData = recyclerDataList[position]

        holder.movieNameTV.text = recyclerData.movieName
        holder.rating1TV.text = recyclerData.rating1TV.toString()
        holder.rating2TV.text = recyclerData.rating2TV.toString()
        holder.ratingMetascoreTV.text = recyclerData.ratingMetascoreTV.toString()
        /*Picasso.get().load(recyclerData.imageUrl).into(holder.movieImageView)

        if (recyclerData.bookmarked){
            holder.bookmarkImageBtn.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
        } else {
            holder.bookmarkImageBtn.setImageResource(R.drawable.ic_baseline_bookmark_add_24)
        }*/

    }
    override fun getItemCount() = recyclerDataList.count()

    fun setData(movie: List<Movie>){
        this.recyclerDataList = movie
        notifyDataSetChanged()
    }
}