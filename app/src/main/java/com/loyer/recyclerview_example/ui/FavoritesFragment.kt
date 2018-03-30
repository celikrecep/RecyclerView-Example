package com.loyer.recyclerview_example.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.loyer.recyclerview_example.R
import com.loyer.recyclerview_example.model.CountryStore
import kotlinx.android.synthetic.main.fragment_favorites.*


class FavoritesFragment : Fragment() {
    private val adapter = CountryAdapter(mutableListOf())

    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewFavorites.layoutManager = LinearLayoutManager(activity)
        recyclerViewFavorites.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val favorites = CountryStore.getFavoriteCountries(activity)
        favorites?.let {
            adapter.updateCountries(favorites)
        }
    }
}
