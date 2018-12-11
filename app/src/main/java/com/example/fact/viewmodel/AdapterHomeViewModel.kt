package com.example.fact.viewmodel

import androidx.databinding.ObservableField
import com.example.fact.model.FactRows

/**
 * Created by Lalit Khandelwal on 11, December, 2018
 * lalitkhandelwal99@gmail.com
 */
class AdapterHomeViewModel(factRows: FactRows) {
    var title = ObservableField(factRows.title)
    var description = ObservableField(factRows.description)
    var imageUrl = ObservableField(factRows.imageHref)

}