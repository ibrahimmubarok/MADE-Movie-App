package com.ibeybeh.made.submission.cataloguefavorite.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ibeybeh.made.submission.cataloguefavorite.R
import com.ibeybeh.made.submission.cataloguefavorite.databinding.FragmentFavoriteBinding
import com.ibeybeh.made.submission.cataloguefavorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    companion object {

        private val tabTitles = arrayOf(
            "Movie",
            "Tv Show"
        )
    }

    private var favoriteBinding: FragmentFavoriteBinding? = null

    private val tabSectionsAdapter: FavoriteSectionsPagerAdapter by lazy {
        FavoriteSectionsPagerAdapter(
            fragment = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return favoriteBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteBinding?.tvTitlePage?.text = resources.getString(R.string.title_favorite)

        loadKoinModules(favoriteModule)

        favoriteBinding?.viewPagerFav?.adapter = tabSectionsAdapter
        favoriteBinding?.tabLayoutFav?.let { tabLayout ->
            favoriteBinding?.viewPagerFav?.let { viewPager ->
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteBinding = null
    }
}