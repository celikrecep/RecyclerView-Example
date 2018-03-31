package com.loyer.recyclerviewexample.model

/**
* Created by loyer on 30.03.2018.
*/
data class City(val id: Int, val name: String, val image: String) {
    val path: String
        get() = "drawable/$image"
}