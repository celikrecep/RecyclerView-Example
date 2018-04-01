package com.loyer.recyclerviewexample.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loyer.recyclerviewexample.R
import com.loyer.recyclerviewexample.app.inflate
import com.loyer.recyclerviewexample.model.Country
import kotlinx.android.synthetic.main.list_item_card_country.view.*

/**
* Created by loyer on 30.03.2018.
*/
class CountryCardAdapter(private val countries : MutableList<Country>) : RecyclerView.Adapter<CountryCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_card_country))

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
            val imageResource = context.resources.getIdentifier(country.uri,null,context.packageName)
            itemView.imgFlag.setImageResource(imageResource)
            itemView.txtName.text = country.name
        }

        override fun onClick(p0: View?) {
            val context = itemView.context
            val intent = CountryActivity.newIntent(context,country.id)
            context.startActivity(intent)
        }

    }

}