package com.loyer.recyclerview_example.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by loyer on 30.03.2018.
 *
 * We will get the photos from the assets and
 * convert the objects to Json representation using Gson
 */
object CountryStore {
    private val TAG = "CountryStore"

    private lateinit var countries: List<Country>
    private lateinit var cities: List<City>

    //we load animals from animals.json with loadJSONFromAsset
    fun loadCountries(context: Context){
        val json = loadJSONFromAsset("countries.json",context)
        //I used TypeToken because it is not possible to  List <Animal>.class
        val listType = object : TypeToken<List<Country>>() {} .type
        //we'll use Gson to convert objects to JSON representation.
        val gson = Gson()
        countries = gson.fromJson(json,listType)
        countries
                .filter { Favorites.isFavorite(it, context) }
                .forEach { it.isFavorite = true }
    }
    //we load animals from foods.json with loadJSONFromAsset
    fun loadCities(context: Context) {
        val json = loadJSONFromAsset("cities.json", context)
        val listType = object : TypeToken<List<City>>() {}.type
        val gson = Gson()
        cities = gson.fromJson(json, listType)
    }

    fun getCountryById(id: Int) = countries.firstOrNull { it.id == id }

    fun getCityById(id: Int) = cities.firstOrNull { it.id == id }

    fun getCountries() = countries

    fun getFavoriteCountries(context: Context) =
            Favorites.getFavorites(context)?.mapNotNull { getCountryById(it) }

    fun getCountryCities(country: Country): List<City> =
            country.cities.mapNotNull { getCityById(it) }

    //we'll load images from Assets with Inputstream
    private fun loadJSONFromAsset(fileName: String, context: Context) : String?{
        var json: String? = null
        try {
            //open asset
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            //we'll file contents  be loaded into memory.
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        }catch (e : Exception){
            e.printStackTrace()
        }
        return json
    }
}