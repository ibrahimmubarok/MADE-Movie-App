package com.ibeybeh.made.submission.cataloguefavorite.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibeybeh.made.submission.cataloguefavorite.R.string
import com.ibeybeh.made.submission.cataloguefavorite.databinding.FragmentFavoriteMovieBinding
import com.ibeybeh.made.submission.cataloguefavorite.viewodel.FavoriteCatalogueViewModel
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.presentation.adapter.MoviesAdapter
import com.ibeybeh.made.submission.core.presentation.adapter.MoviesAdapter.MovieCallback
import com.ibeybeh.made.submission.mademovieapp.presentation.detail.DetailActivity
import com.ibeybeh.made.submission.mademovieapp.utils.Const
import com.ibeybeh.made.submission.mademovieapp.utils.ext.setVisibility
import kotlinx.android.synthetic.main.layout_empty_view.tvDescEmpty
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment(), MovieCallback {

    private var favoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val favoriteViewModel: FavoriteCatalogueViewModel by viewModel()

    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(
            mutableListOf(),
            context = requireContext(),
            listener = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return favoriteMovieBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteMovieBinding?.pbMovie?.setVisibility(true)

        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        favoriteViewModel.getFavoriteMovies().observe(viewLifecycleOwner, { data ->
            if (data.isNotEmpty()) {
                favoriteMovieBinding?.pbMovie?.setVisibility(false)
                favoriteMovieBinding?.layoutEmpty?.emptyLayout?.setVisibility(false)
                moviesAdapter.setData(data)
            } else {
                favoriteMovieBinding?.pbMovie?.setVisibility(false)
                favoriteMovieBinding?.layoutEmpty?.emptyLayout?.setVisibility(true)
                tvDescEmpty.text = getString(string.label_add_movie_to_favorite)
            }
        })
    }

    private fun initRecyclerView() {
        favoriteMovieBinding?.rvMovie?.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun movieOnClicked(data: Movie) {
        val classname = resources.getString(com.ibeybeh.made.submission.mademovieapp.R.string.title_movie)
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(Const.EXTRA_MOVIE, data)
            putExtra(Const.EXTRA_CLASSNAME, classname)
        })
    }

    override fun onDestroyView() {
        favoriteMovieBinding?.rvMovie?.adapter = null
        favoriteMovieBinding = null
        super.onDestroyView()
    }
}