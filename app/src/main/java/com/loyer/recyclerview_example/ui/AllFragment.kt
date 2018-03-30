package com.loyer.recyclerview_example.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.loyer.recyclerview_example.R
import com.loyer.recyclerview_example.model.CountryStore
import kotlinx.android.synthetic.main.fragment_all.*


class AllFragment : Fragment() {
    private val adapter = CountryAdapter(CountryStore.getCountries().toMutableList())

    companion object {
        fun newInstance(): AllFragment {
            return AllFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_all, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCountry.layoutManager = LinearLayoutManager(activity)
        recyclerViewCountry.adapter = adapter
    }
}
