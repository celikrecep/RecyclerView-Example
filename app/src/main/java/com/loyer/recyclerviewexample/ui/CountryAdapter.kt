package com.loyer.recyclerviewexample.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loyer.recyclerviewexample.R
import com.loyer.recyclerviewexample.app.inflate
import com.loyer.recyclerviewexample.model.Country
import kotlinx.android.synthetic.main.list_item_country.view.*

/**
* Created by loyer on 30.03.2018.
*/
class CountryAdapter(private val countries : MutableList<Country>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_country))

    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)

        }
        private lateinit var country: Country
        fun bind(country: Country){
            this.country = country
            val context = itemView.context

            itemView.imgFlag.setImageResource(context.resources.getIdentifier(country.uri,null,context.packageName))
            itemView.txtName.text = country.name
            itemView.txtCapital.text = country.capital
        }

        override fun onClick(p0: View?) {
            val context = itemView.context
            val intent = CountryActivity.newIntent(context,country.id)
            context.startActivity(intent)
        }
    }
    fun updateCountries(countries: List<Country>){
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }
}