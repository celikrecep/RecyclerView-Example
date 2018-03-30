package com.loyer.recyclerview_example.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.loyer.recyclerview_example.R
import com.loyer.recyclerview_example.model.CountryStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CountryStore.loadCountries(this)
        CountryStore.loadCities(this)

        setupNavigationBar()
        setupViewPager()
    }

    private fun setupNavigationBar() {
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_all -> {
                title = getString(R.string.country)
                viewPager.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                title = getString(R.string.title_favorites)
                viewPager.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setupViewPager() {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment? {
                when (position) {
                    0 -> return AllFragment.newInstance()
                    1 -> return FavoritesFragment.newInstance()
                }
                return null
            }

            override fun getCount() = 2
        }
    }
}
