package com.hanna.pagingmovies.presentation.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.hanna.pagingmovies.databinding.FragmentDetailsBinding
import com.hanna.pagingmovies.presentation.common.LoadingBar
import com.hanna.pagingmovies.presentation.detail.adapter.DetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()

    private val adapter by lazy { DetailsAdapter() }

    private val loading by lazy { LoadingBar(requireContext()) }

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDetailList.adapter = adapter
        binding.layoutDetailError.btnRefresh.setOnClickListener {
            adapter.refresh()
        }
        observeData()
        setupYoutubeTrailerPlayer()
    }

    private fun setupYoutubeTrailerPlayer() {
        adapter.setupWatchTrailerListener {
            it.videoKey?.let { url -> startYoutubeVideo(url) }
        }
    }

    private fun startYoutubeVideo(key: String) {
        val url = "http://www.youtube.com/watch?v=$key"
        try {
            val appInfo = context?.packageManager?.getApplicationInfo("com.google.android.youtube", 0)
            if (appInfo?.enabled == true) {
                val uri: Uri = Uri.parse("vnd.youtube:$key")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        } catch (e: Exception) {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            )
        }
    }


    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getMovieDetails(args.movieId).collectLatest {
                        adapter.submitData(it)
                    }
                }
                launch {
                    adapter.loadStateFlow.collectLatest { loadState ->
                        when (loadState.refresh) {
                            is LoadState.NotLoading -> {
                                loading.hide()
                                val isEmpty = adapter.itemCount == 0
                                binding.layoutDetailError.root.isVisible = isEmpty
                                binding.rvDetailList.isVisible = !isEmpty
                            }
                            LoadState.Loading -> {
                                loading.show()
                                binding.layoutDetailError.root.isVisible = false
                            }
                            is LoadState.Error -> {
                                loading.hide()
                                binding.layoutDetailError.root.isVisible = true
                                binding.rvDetailList.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvDetailList.adapter = null
        _binding = null
    }
}