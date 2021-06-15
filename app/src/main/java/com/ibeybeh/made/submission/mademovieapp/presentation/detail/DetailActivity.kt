package com.ibeybeh.made.submission.mademovieapp.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ibeybeh.made.submission.core.domain.model.Movie
import com.ibeybeh.made.submission.core.domain.model.TvShow
import com.ibeybeh.made.submission.core.utils.emptyString
import com.ibeybeh.made.submission.mademovieapp.R
import com.ibeybeh.made.submission.mademovieapp.R.string
import com.ibeybeh.made.submission.mademovieapp.databinding.ActivityDetailBinding
import com.ibeybeh.made.submission.mademovieapp.presentation.detail.viewmodel.DetailViewModel
import com.ibeybeh.made.submission.mademovieapp.utils.Const.EXTRA_CLASSNAME
import com.ibeybeh.made.submission.mademovieapp.utils.Const.EXTRA_MOVIE
import com.ibeybeh.made.submission.mademovieapp.utils.Const.EXTRA_TV_SHOW
import com.ibeybeh.made.submission.core.utils.ext.setImageUrl
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var detailBinding: ActivityDetailBinding? = null

    private val detailCatalogueViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(detailBinding?.root)

        setSupportActionBar(detailBinding?.toolbar?.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = emptyString()

        val classname = intent.getStringExtra(EXTRA_CLASSNAME) ?: emptyString()
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        val tvShow = intent.getParcelableExtra<TvShow>(EXTRA_TV_SHOW)

        when (classname) {
            resources.getString(R.string.title_movie) -> {
                movie?.let { movieData ->
                    showDetailMovie(movieData)
                }
            }
            resources.getString(R.string.title_tvshow) -> {
                tvShow?.let { tvShowData ->
                    showDetailTvShow(tvShowData)
                }
            }
        }
    }

    private fun showDetailMovie(movie: Movie) {
        detailBinding?.tvDetailTitle?.text = movie.title
        detailBinding?.tvDetailDesc?.text = movie.overview
        detailBinding?.tvDetailRating?.text = movie.voteAverage.toString()
        detailBinding?.tvDetailRelease?.text = movie.releaseDate

        val rating = movie.voteAverage.div(2)
        detailBinding?.ratingBarDetail?.rating = rating.toFloat()

        detailBinding?.pbBanner?.let {
            detailBinding?.imgBackdropDetail?.setImageUrl(
                context = this,
                imgUrl = movie.backdropPath,
                progressBar = it
            )
        }

        var statusFav = movie.isFavorite
        setStatusFavorite(statusFav)
        detailBinding?.btnFavorite?.setOnClickListener {
            statusFav = !statusFav
            detailCatalogueViewModel.setFavoriteMovie(movie)
            setStatusFavorite(statusFav)
            showToast(statusFav)
        }
    }

    private fun showDetailTvShow(tvShow: TvShow) {
        detailBinding?.tvDetailTitle?.text = tvShow.name
        detailBinding?.tvDetailDesc?.text = tvShow.overview
        detailBinding?.tvDetailRating?.text = tvShow.voteAverage.toString()
        detailBinding?.tvDetailRelease?.text = tvShow.firstAirDate

        val rating = tvShow.voteAverage.div(2)
        detailBinding?.ratingBarDetail?.rating = rating.toFloat()

        detailBinding?.pbBanner?.let {
            detailBinding?.imgBackdropDetail?.setImageUrl(
                context = this,
                imgUrl = tvShow.backdropPath,
                progressBar = it
            )
        }

        var statusFav = tvShow.isFavorite
        setStatusFavorite(statusFav)
        detailBinding?.btnFavorite?.setOnClickListener {
            statusFav = !statusFav
            tvShow.let { data ->
                detailCatalogueViewModel.setFavoriteTvShow(data)
            }
            setStatusFavorite(statusFav)
            showToast(statusFav)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            detailBinding?.btnFavorite?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked))
        } else {
            detailBinding?.btnFavorite?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_bookmark))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    private fun showToast(boolean: Boolean) {
        if (boolean) {
            Toast.makeText(this, getString(string.action_berhasil_menambahkan), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(string.action_berhasil_dihapus), Toast.LENGTH_SHORT).show()
        }
    }
}