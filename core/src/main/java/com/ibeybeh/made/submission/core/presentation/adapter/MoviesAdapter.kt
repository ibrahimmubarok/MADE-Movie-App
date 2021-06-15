package com.ibeybeh.made.submission.core.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibeybeh.made.submission.core.R
import com.ibeybeh.made.submission.core.databinding.ItemRowListBinding
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.utils.ext.setImageUrl

class MoviesAdapter(
    private var data: MutableList<Movie>,
    private val context: Context,
    val listener: MovieCallback? = null
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    fun setData(data: List<Movie>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemRowListBinding.bind(view)

        fun bind(data: Movie) {
            with(binding) {
                tvTitleItemCatalogue.text = data.title
                tvDateItemCatalogue.text = data.releaseDate

                val rating = data.voteAverage.div(2)
                ratingBarItemCatalogue.rating = rating.toFloat()

                imgItemCatalogue.setImageUrl(context, data.posterPath, pbItemCatalogue)

                itemView.setOnClickListener {
                    listener?.movieOnClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_list, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface MovieCallback {

        fun movieOnClicked(data: Movie)
    }
}