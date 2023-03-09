package com.hanna.pagingmovies.presentation.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanna.pagingmovies.databinding.ItemGenreBinding
import com.hanna.pagingmovies.domain.model.GenreUiModel

class GenreAdapter: RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private var onClickListener: ((GenreUiModel) -> Unit)? = null
    private val data = mutableListOf<GenreUiModel>()

    fun setOnClickListener(listener: (GenreUiModel) -> Unit) {
        onClickListener = listener
    }

    fun submitData(genres: List<GenreUiModel>) {
        val size = data.size
        data.addAll(genres)
        notifyItemRangeInserted(size, genres.size)
    }

    class ViewHolder(
        private val binding: ItemGenreBinding,
        private val onClickListener: ((GenreUiModel) -> Unit)?
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: GenreUiModel) {
            binding.root.setOnClickListener {
                onClickListener?.invoke(model)
            }
            binding.root.text = model.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onClickListener)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}