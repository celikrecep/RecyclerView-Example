package com.loyer.recyclerview_example.model

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by loyer on 30.03.2018.
 *
 * We will add and remove favorites
 * and save them with SharedPreferences.
 */

object Favorites {
    private val KEY_FAVORITES = "FAVORITES"
    private val gson = Gson()

    private var favorites: MutableList<Int>? = null

    //check favorite control
    fun isFavorite(country: Country, context: Context): Boolean {
        return getFavorites(context)?.contains(country.id) == true
    }
    fun addFavorite(country: Country, context: Context) {
        val favorites = getFavorites(context)
        //if it not null add it
        favorites?.let {
            country.isFavorite = true
            favorites.add(country.id)
            saveFavorites(KEY_FAVORITES, favorites, context)
        }
    }

    fun removeFavorite(country: Country, context: Context) {
        val favorites = getFavorites(context)
        //if it not null remove it
        favorites?.let {
            country.isFavorite = false
            favorites.remove(country.id)
            saveFavorites(KEY_FAVORITES, favorites, context)
        }
    }

    fun getFavorites(context: Context): MutableList<Int>? {
        if (favorites == null) {
            val json = sharedPrefs(context).getString(KEY_FAVORITES, "")
            val type = object : TypeToken<MutableList<Int>>() {}.type
            favorites = gson.fromJson<MutableList<Int>>(json, type) ?: return mutableListOf()
        }
        return favorites
    }

    private fun saveFavorites(key: String, list: List<Int>, context: Context) {
        val json = gson.toJson(list)
        sharedPrefs(context).edit().putString(key, json).apply()
    }
    //shared preferences instance
    private fun sharedPrefs(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)
}