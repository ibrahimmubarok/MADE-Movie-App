package com.ibeybeh.made.submission.cataloguefavorite.presentation.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibeybeh.made.submission.cataloguefavorite.R
import com.ibeybeh.made.submission.cataloguefavorite.R.string
import com.ibeybeh.made.submission.cataloguefavorite.databinding.FragmentFavoriteTvShowBinding
import com.ibeybeh.made.submission.cataloguefavorite.viewodel.FavoriteCatalogueViewModel
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.presentation.adapter.TvShowAdapter
import com.ibeybeh.made.submission.core.presentation.adapter.TvShowAdapter.TvShowCallback
import com.ibeybeh.made.submission.mademovieapp.presentation.detail.DetailActivity
import com.ibeybeh.made.submission.mademovieapp.utils.Const
import com.ibeybeh.made.submission.mademovieapp.utils.ext.setVisibility
import kotlinx.android.synthetic.main.layout_empty_view.tvDescEmpty
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment(), TvShowCallback {

    private var favoriteTvShowBinding: FragmentFavoriteTvShowBinding? = null
    private val favoriteViewModel: FavoriteCatalogueViewModel by viewModel()

    private val tvShowAdapter: TvShowAdapter by lazy {
        TvShowAdapter(
            mutableListOf(),
            context = requireContext(),
            listener = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return favoriteTvShowBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteTvShowBinding?.pbTvShow?.setVisibility(true)

        initViewModel()
        initRecyclerView()
    }

    private fun initViewModel() {
        favoriteViewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { data ->
            if (data.isNotEmpty()) {
                favoriteTvShowBinding?.pbTvShow?.setVisibility(false)
                favoriteTvShowBinding?.layoutEmpty?.emptyLayout?.setVisibility(false)
                tvShowAdapter.setData(data)
            } else {
                favoriteTvShowBinding?.pbTvShow?.setVisibility(false)
                favoriteTvShowBinding?.layoutEmpty?.emptyLayout?.setVisibility(true)
                tvDescEmpty.text = getString(string.label_tvshow_to_favorite)
            }
        })
    }

    private fun initRecyclerView() {
        favoriteTvShowBinding?.rvTvShow?.apply {
            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun tvShowOnClicked(tvShow: TvShow) {
        val classname = resources.getString(R.string.title_tvshow)
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(Const.EXTRA_TV_SHOW, tvShow)
            putExtra(Const.EXTRA_CLASSNAME, classname)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteTvShowBinding = null
    }
}