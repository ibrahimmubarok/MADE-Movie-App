package com.ibeybeh.made.submission.core.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibeybeh.made.submission.core.R
import com.ibeybeh.made.submission.core.databinding.ItemRowListBinding
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.mademovieapp.utils.ext.setImageUrl

class TvShowAdapter(
    private var data: MutableList<TvShow>,
    private var context: Context,
    var listener: TvShowCallback? = null
) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    fun setData(data: List<TvShow>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemRowListBinding.bind(view)

        fun bind(data: TvShow) {
            with(binding) {
                tvTitleItemCatalogue.text = data.name
                tvDateItemCatalogue.text = data.firstAirDate

                val rating = data.voteAverage.div(2)
                ratingBarItemCatalogue.rating = rating.toFloat()

                imgItemCatalogue.setImageUrl(context, data.posterPath, pbItemCatalogue)

                itemView.setOnClickListener {
                    listener?.tvShowOnClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_list, parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface TvShowCallback {
        fun tvShowOnClicked(tvShow: TvShow)
    }
}