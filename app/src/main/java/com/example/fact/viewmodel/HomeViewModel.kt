package com.example.fact.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.fact.api.AppAPIs
import com.example.fact.api.error.GetRetroFitError
import com.example.fact.global.isNotEmptyAndNull
import com.example.fact.global.rxjava.SchedulerProvider
import com.example.fact.model.FactResponse
import com.example.fact.model.FactRows
import com.example.fact.navigator.HomeNavigator
import com.example.fact.view.base.BaseViewModel

/**
 * Created by Lalit Khandelwal on 11, December, 2018
 * lalitkhandelwal99@gmail.com
 */
class HomeViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider) :
    BaseViewModel<HomeNavigator>(appAPIs, schedulerProvider) {
    var title = ObservableField("Fact")
    var factRowsListResponse: MutableLiveData<FactResponse>? = null

    init {
        factRowsListResponse = MutableLiveData()
    }

    /**
     * Get fact data from server
     */
    fun getFactData() {
        navigator?.onRefresh(true)
        compositeDisposable.add(
            appAPIs.getFactData()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    navigator?.onRefresh(false)
                    factRowsListResponse?.value = response
                    title.set(response.title)
                }, { throwable ->
                    navigator?.onRefresh(false)
                    GetRetroFitError(navigator, throwable)
                })
        )
    }

    /**
     * Verify server data, if all key data coming blank, will not add
     */
    fun checkData(factRowsList: List<FactRows>): List<FactRows> {
        val list = ArrayList<FactRows>()
        if (!factRowsList.isNullOrEmpty()) {
            for (i in 0 until factRowsList.size) {
                val row = factRowsList[i]
                if (row.title.isNotEmptyAndNull() ||
                    row.description.isNotEmptyAndNull() ||
                    row.imageHref.isNotEmptyAndNull()
                ) list.add(row)
            }
        }
        return list
    }
}