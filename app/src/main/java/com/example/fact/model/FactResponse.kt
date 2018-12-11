package com.example.fact.model


/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
data class FactResponse(
    val rows: List<FactRows>,
    val title: String
)

data class FactRows(
    val description: String,
    val imageHref: String,
    val title: String
)