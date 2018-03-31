package com.loyer.recyclerviewexample.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.loyer.recyclerviewexample.R
import com.loyer.recyclerviewexample.app.inflate
import com.loyer.recyclerviewexample.model.Country
import com.loyer.recyclerviewexample.model.CountryStore
import kotlinx.android.synthetic.main.list_item_country_with_cities.view.*


/**
* Created by loyer on 31.03.2018.
 * Recycled view pools allow multiple RecyclerViews to share a common pool of scrap views.
 * So it can use each other’s scraped views. Which gives much lesser view creation and better scroll performance.
 *
*/
class CountryWithCitiesAdapter(private val countries : MutableList<Country>) : RecyclerView.Adapter<CountryWithCitiesAdapter.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(parent.inflate(R.layout.list_item_country_with_cities))
        //will snap the center of the target child view to the center of the attached RecyclerView.
        holder.itemView.recyclerViewCity.recycledViewPool = viewPool
        LinearSnapHelper().attachToRecyclerView(holder.itemView.recyclerViewCity)
        return holder
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val adapter = CityAdapter(mutableListOf())

        private lateinit var country: Country
        fun bind(country: Country){
            this.country = country
            val context = itemView.context

            itemView.imgFlag.setImageResource(context.resources.getIdentifier(country.uri,null,context.packageName))
            itemView.txtCapital.text = country.capital
            setupCities()
        }

        override fun onClick(p0: View?) {
            val context = itemView.context
            val intent = CountryActivity.newIntent(context,country.id)
            context.startActivity(intent)
        }

        private fun setupCities(){
            itemView.recyclerViewCity.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
            itemView.recyclerViewCity.adapter = adapter

            val cities = CountryStore.getCountryCities(country)
            adapter.updateCities(cities)
        }
    }
}
