package com.loyer.recyclerviewexample.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.loyer.recyclerviewexample.R
import com.loyer.recyclerviewexample.model.Country
import com.loyer.recyclerviewexample.model.CountryStore
import com.loyer.recyclerviewexample.model.Favorites
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : AppCompatActivity() {

    private val adapter = CityAdapter(mutableListOf())
    private lateinit var country: Country

    companion object {
        private const val INTENT_COUNTRY_ID = "INTEN_COUNTRY-ID"
        //when allFragment view clicked
        fun newIntent(context: Context, countryID: Int) : Intent{
            val intent = Intent(context,CountryActivity::class.java)
            intent.putExtra(INTENT_COUNTRY_ID, countryID)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        setupCountry()
        setupTitle()
        setupViews()
        setupFavoriteButton()
        setupCities()
    }

    private fun setupCountry(){
        val countryId = CountryStore.getCountryById(intent.getIntExtra(INTENT_COUNTRY_ID,1))
        if(countryId == null){
            Toast.makeText(this, getString(R.string.invalid_country), Toast.LENGTH_SHORT).show()
            finish()
        }else{
            country = countryId
        }
    }

    private fun setupTitle(){
        title = String.format(getString(R.string.detail_title_format),country.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews(){
        imgHeader.setImageResource(resources.getIdentifier(country.uri,null,packageName))
        txtName.text = country.name
        txtCapital.text = country.capital
    }

    private fun setupFavoriteButton(){

        setupFavoriteButtonImage(country)
        setupFavoriteButtonClickListener(country)
    }

    private fun setupFavoriteButtonImage(country: Country){
        if(country.isFavorite){
            btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
        }else{
            btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
        }
    }

    private fun setupFavoriteButtonClickListener(country: Country){
        btnFavorite.setOnClickListener {
            if (country.isFavorite) {
                btnFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_border))
                Favorites.removeFavorite(country, this)
            } else {
                btnFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite))
                Favorites.addFavorite(country, this)
            }
        }
    }

    fun setupCities(){
        recyclerViewCity.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewCity.adapter = adapter

        val cities = CountryStore.getCountryCities(country)
        adapter.updateCities(cities)
    }
}
