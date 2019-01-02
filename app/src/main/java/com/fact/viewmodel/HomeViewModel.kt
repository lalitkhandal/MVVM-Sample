package com.fact.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.fact.api.AppAPIs
import com.fact.api.error.GetRetroFitError
import com.fact.global.isNotEmptyAndNull
import com.fact.global.rxjava.SchedulerProvider
import com.fact.model.FactResponse
import com.fact.model.FactRows
import com.fact.navigator.HomeNavigator
import com.fact.view.base.BaseViewModel
import io.reactivex.disposables.Disposable

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
    private var disposable: Disposable? = null

    fun getFactData() {
        navigator?.onRefresh(true)
        disposable = appAPIs.getFactData()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                navigator?.onRefresh(false)
                factRowsListResponse?.value = response
                title.set(response.title)
            }, { throwable ->
                navigator?.onRefresh(false)
                GetRetroFitError(navigator, throwable)
            }, {

            })
        compositeDisposable.add(disposable!!)
    }

    fun cancelRequest() {
        disposable?.dispose()
        navigator?.onRefresh(false)
        navigator?.onTimeout()
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