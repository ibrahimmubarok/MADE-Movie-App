package com.ibeybeh.made.submission.cataloguefavorite.presentation.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibeybeh.made.submission.cataloguefavorite.presentation.movie.FavoriteMovieFragment
import com.ibeybeh.made.submission.cataloguefavorite.presentation.tvshow.FavoriteTvShowFragment

class FavoriteSectionsPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }
    }
}