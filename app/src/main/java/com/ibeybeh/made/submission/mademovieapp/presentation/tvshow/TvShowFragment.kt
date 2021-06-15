package com.ibeybeh.made.submission.mademovieapp.presentation.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibeybeh.made.submission.core.data.Resource
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.presentation.adapter.TvShowAdapter
import com.ibeybeh.made.submission.core.presentation.adapter.TvShowAdapter.TvShowCallback
import com.ibeybeh.made.submission.mademovieapp.BuildConfig
import com.ibeybeh.made.submission.mademovieapp.R
import com.ibeybeh.made.submission.mademovieapp.databinding.FragmentTvShowBinding
import com.ibeybeh.made.submission.mademovieapp.presentation.detail.DetailActivity
import com.ibeybeh.made.submission.mademovieapp.presentation.tvshow.viewmodel.TvShowViewModel
import com.ibeybeh.made.submission.mademovieapp.utils.Const
import com.ibeybeh.made.submission.core.utils.ext.setVisibility
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(), TvShowCallback {

    private var tvShowBinding: FragmentTvShowBinding? = null

    private val tvShowAdapter: TvShowAdapter by lazy {
        TvShowAdapter(
            mutableListOf(),
            context = requireContext(),
            listener = this
        )
    }

    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return tvShowBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowBinding?.tvTitlePage?.text = resources.getString(R.string.title_tvshow)

        initRecyclerView()
        getAllTvShows()
    }

    private fun initRecyclerView() {
        tvShowBinding?.rvTvShow?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
        }
    }

    private fun getAllTvShows() {
        tvShowViewModel.getAllTvShows(BuildConfig.API_KEY, 1, "en-US").observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        tvShowBinding?.pbTvShow?.setVisibility(true)
                    }
                    is Resource.Success -> {
                        tvShowBinding?.pbTvShow?.setVisibility(false)
                        tvShowBinding?.layoutError?.errorLayout?.setVisibility(false)

                        tvShow.data?.let { list ->
                            tvShowAdapter.setData(list)
                        }
                    }
                    is Resource.Error -> {
                        tvShowBinding?.pbTvShow?.setVisibility(false)
                        tvShowBinding?.layoutError?.errorLayout?.setVisibility(true)

                        tvShowBinding?.layoutError?.tvDescError?.text = tvShow.message
                        tvShowBinding?.layoutError?.btnError?.setOnClickListener {
                            tvShowViewModel.getAllTvShows(BuildConfig.API_KEY, 1, "en-US")
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        tvShowBinding?.rvTvShow?.adapter = null
        tvShowBinding = null
        super.onDestroyView()
    }

    override fun tvShowOnClicked(tvShow: TvShow) {
        val classname = resources.getString(R.string.title_tvshow)
        startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(Const.EXTRA_TV_SHOW, tvShow)
            putExtra(Const.EXTRA_CLASSNAME, classname)
        })
    }
}