package com.hanna.pagingmovies.presentation.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanna.pagingmovies.databinding.ItemDiscoverBinding
import com.hanna.pagingmovies.domain.model.MovieUiModel

class DiscoverAdapter: PagingDataAdapter<MovieUiModel, DiscoverAdapter.ViewHolder>(DiffCallback()) {

    private var onClickListener: ((MovieUiModel) -> Unit)? = null

    fun setOnClickListener(listener: (MovieUiModel) -> Unit) {
        onClickListener = listener
    }

    class ViewHolder(
        private val binding: ItemDiscoverBinding,
        private val listener: ((MovieUiModel) -> Unit)?
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieUiModel) {
            binding.root.setOnClickListener {
                listener?.invoke(model)
            }
            with(binding) {
                Glide.with(itemView.context)
                    .load(model.posterUrl)
                    .into(ivMoviePoster)
                tvMovieTitle.text = model.title
                tvMovieVote.text = model.vote.toString()
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<MovieUiModel>() {
        override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDiscoverBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClickListener)
    }

}