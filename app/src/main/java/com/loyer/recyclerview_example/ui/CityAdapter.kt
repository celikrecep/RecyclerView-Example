package com.loyer.recyclerview_example.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loyer.recyclerview_example.R
import com.loyer.recyclerview_example.app.inflate
import com.loyer.recyclerview_example.model.City
import kotlinx.android.synthetic.main.list_item_city.view.*

/**
* Created by loyer on 30.03.2018.
*/
class CityAdapter(private val cities: MutableList<City>) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_city))
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    fun updateCities(cities: List<City>){
        this.cities.clear()
        this.cities.addAll(cities)
        notifyDataSetChanged()
    }

    class ViewHolder(iteView: View): RecyclerView.ViewHolder(iteView){
        private lateinit var city: City
        fun bind(city: City){
            this.city = city
            val context = itemView.context

            itemView.imgCity.setImageResource(context.resources.getIdentifier(city.path,null,context.packageName))
            itemView.txtCityName.text = city.name
        }
    }
}