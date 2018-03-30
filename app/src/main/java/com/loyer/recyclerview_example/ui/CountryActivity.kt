package com.loyer.recyclerview_example.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.loyer.recyclerview_example.R
import com.loyer.recyclerview_example.model.Country
import com.loyer.recyclerview_example.model.CountryStore
import com.loyer.recyclerview_example.model.Favorites
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : AppCompatActivity() {

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
}
