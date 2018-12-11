package com.example.fact.navigator

/**
 * Created by Lalit Khandelwal on 11, December, 2018
 * lalitkhandelwal99@gmail.com
 */
interface HomeNavigator : ErrorNavigator {

    fun onRefresh(isRefresh: Boolean)
}