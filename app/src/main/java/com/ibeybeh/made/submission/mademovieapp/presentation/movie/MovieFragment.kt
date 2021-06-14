package com.ibeybeh.made.submission.mademovieapp.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.presentation.adapter.MoviesAdapter
import com.ibeybeh.made.submission.core.presentation.adapter.MoviesAdapter.MovieCallback
import com.ibeybeh.made.submission.mademovieapp.BuildConfig
import com.ibeybeh.made.submission.mademovieapp.R
import com.ibeybeh.made.submission.mademovieapp.databinding.FragmentMovieBinding
import com.ibeybeh.made.submission.mademovieapp.presentation.detail.DetailActivity
import com.ibeybeh.made.submission.mademovieapp.presentation.movie.viewmodel.MovieViewModel
import com.ibeybeh.made.submission.mademovieapp.utils.Const.EXTRA_CLASSNAME
import com.ibeybeh.made.submission.mademovieapp.utils.Const.EXTRA_MOVIE
import com.ibeybeh.made.submission.mademovieapp.utils.ext.setVisibility
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), MovieCallback {

    private var movieBinding: FragmentMovieBinding? = null

    private val movieAdapter: MoviesAdapter by lazy {
        MoviesAdapter(
            mutableListOf(),
            context = requireContext(),
            listener = this
        )
    }

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieBinding = FragmentMovieBinding.inflate(inflater, container, false)

        return movieBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieBinding?.tvTitlePage?.text = resources.getString(R.string.title_movie)
        movieBinding?.pbMovie?.setVisibility(true)

        initRecyclerView()
        getAllMovies()
    }

    private fun getAllMovies() {
        movieViewModel.getAllMovies(BuildConfig.API_KEY, 1, "en-US").observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        movieBinding?.pbMovie?.setVisibility(true)
                    }
                    is Resource.Success -> {
                        movieBinding?.pbMovie?.setVisibility(false)
                        movieBinding?.layoutError?.errorLayout?.setVisibility(false)

                        movies.data?.let { list ->
                            movieAdapter.setData(list)
                        }
                    }
                    is Resource.Error -> {
                        movieBinding?.pbMovie?.setVisibility(false)
                        movieBinding?.layoutError?.errorLayout?.setVisibility(true)

                        movieBinding?.layoutError?.tvDescError?.text = movies.message
                        movieBinding?.layoutError?.btnError?.setOnClickListener {
                            movieViewModel.getAllMovies(BuildConfig.API_KEY, 1, "en-US")
                        }
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        movieBinding?.rvMovie?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        movieBinding?.rvMovie?.adapter = null
        movieBinding = null
        super.onDestroyView()
    }

    override fun movieOnClicked(data: Movie) {
        val classname = resources.getString(R.string.title_movie)
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(EXTRA_MOVIE, data)
            putExtra(EXTRA_CLASSNAME, classname)
        })
    }
}