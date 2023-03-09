package com.hanna.pagingmovies.presentation.discover

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.hanna.pagingmovies.databinding.FragmentDiscoverBinding
import com.hanna.pagingmovies.domain.model.MovieUiModel
import com.hanna.pagingmovies.presentation.common.LoadingBar
import com.hanna.pagingmovies.presentation.common.extension.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { DiscoverAdapter() }

    private val viewModel by viewModels<DiscoverViewModel>()

    private val loading by lazy { LoadingBar(requireContext()) }

    private val args by navArgs<DiscoverFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDiscoverList.adapter = adapter
        binding.layoutDiscoverError.btnRefresh.setOnClickListener {
            adapter.refresh()
        }
        adapter.setOnClickListener(::goToDetailsScreen)
        observeData()
    }

    private fun goToDetailsScreen(movie: MovieUiModel) {
        val action = DiscoverFragmentDirections
            .actionDiscoveryFragmentToDetailsFragment(movie.title, movie.id)
        findNavController().safeNavigate(action)
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.fetchMovieDiscovers(args.genreId).collectLatest {
                        adapter.submitData(it)
                    }
                }
                launch {
                    adapter.loadStateFlow.collectLatest { loadState ->
                        when (loadState.refresh) {
                            is LoadState.NotLoading -> {
                                loading.hide()
                                val isEmpty = adapter.itemCount == 0
                                binding.layoutDiscoverError.root.isVisible = isEmpty
                                binding.rvDiscoverList.isVisible = !isEmpty
                            }
                            LoadState.Loading -> {
                                loading.show()
                                binding.layoutDiscoverError.root.isVisible = false
                            }
                            is LoadState.Error -> {
                                loading.hide()
                                binding.layoutDiscoverError.root.isVisible = true
                                binding.rvDiscoverList.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}