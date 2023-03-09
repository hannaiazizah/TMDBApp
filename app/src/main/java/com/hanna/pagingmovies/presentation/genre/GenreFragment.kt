package com.hanna.pagingmovies.presentation.genre

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
import com.hanna.pagingmovies.databinding.FragmentGenreBinding
import com.hanna.pagingmovies.domain.core.onFailure
import com.hanna.pagingmovies.domain.core.onSuccess
import com.hanna.pagingmovies.presentation.common.LoadingBar
import com.hanna.pagingmovies.presentation.common.extension.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { GenreAdapter() }

    private val viewModel by viewModels<GenreViewModel>()

    private val loading by lazy { LoadingBar(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getGenres()
        binding.rvGenreList.adapter = adapter
        adapter.setOnClickListener{
            navigateToDiscoverMovies(it.id)
        }
        binding.layoutGenreError.btnRefresh.setOnClickListener {
            viewModel.getGenres()
        }
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.isLoading.collectLatest {
                        if (it) loading.show() else loading.hide()
                    }
                }
                launch {
                    viewModel.genreResult.collectLatest {
                        it.onSuccess { data ->
                            binding.rvGenreList.isVisible = true
                            binding.layoutGenreError.root.isVisible = false
                            adapter.submitData(data)
                        }
                        it.onFailure {
                            binding.rvGenreList.isVisible = false
                            binding.layoutGenreError.root.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDiscoverMovies(genreId: Int) {
        val action = GenreFragmentDirections.actionGenreFragmentToDiscoveryFragment(genreId)
        findNavController().safeNavigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}