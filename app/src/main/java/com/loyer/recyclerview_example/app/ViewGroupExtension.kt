package com.loyer.recyclerview_example.app

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
* Created by loyer on 30.03.2018.
*/

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachtoRoot: Boolean = false) : View{
    return LayoutInflater.from(context).inflate(layoutRes,this,attachtoRoot)
}
