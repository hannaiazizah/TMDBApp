package com.hanna.pagingmovies.presentation.detail.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.hanna.pagingmovies.R
import com.hanna.pagingmovies.databinding.ItemMovieDetailsBinding
import com.hanna.pagingmovies.databinding.ItemReviewBinding
import com.hanna.pagingmovies.domain.model.DetailUiModel
import com.hanna.pagingmovies.domain.model.DetailsUiModel
import com.hanna.pagingmovies.domain.model.ReviewUiModel

sealed class DetailsViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root)

class DetailViewHolder(
    private val binding: ItemMovieDetailsBinding,
    private val onWatchTrailerListener: ((DetailUiModel) -> Unit)?
): DetailsViewHolder(binding) {
    fun bind(model: DetailsUiModel?) {
        if (model !is DetailUiModel) return
        with(binding) {
            Glide.with(itemView.context)
                .load(model.backgroundImage)
                .into(ivDetailBackground)
            tvDetailOverview.text = model.overview
            tvDetailsTitle.text = model.title
            tvDetailReview.text = model.totalReview
            tvDetailReleaseDate.text = model.releaseDate
            model.genre.forEach {
                val chip = Chip(itemView.context)
                chip.text = it.name
                cgDetailGenre.addView(chip)
            }
            btnDetailTrailer.setOnClickListener {
                onWatchTrailerListener?.invoke(model)
            }
            binding.btnDetailTrailer.isVisible = model.videoKey != null
        }
    }
}

class ReviewViewHolder(
    private val binding: ItemReviewBinding
): DetailsViewHolder(binding) {
    fun bind(model: DetailsUiModel?) {
        if (model !is ReviewUiModel) return
        with(binding) {
            Glide.with(itemView.context)
                .load(model.avatar)
                .placeholder(R.drawable.bg_circle_green)
                .circleCrop()
                .into(ivReviewAvatar)
            tvReviewContent.text = model.content
            tvReviewDate.text = model.date
            tvReviewUsername.text = model.author
        }
    }
}