package com.loyer.recyclerviewexample.model

/**
* Created by loyer on 30.03.2018.
*/
data class Country(val id: Int,
                   val name: String,
                   val capital: String,
                   val image: String,
                   val cities: List<Int>,
                   var isFavorite: Boolean = false) {
    val uri: String
        get() = "drawable/$image"
}