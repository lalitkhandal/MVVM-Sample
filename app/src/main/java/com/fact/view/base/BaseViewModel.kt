package com.fact.view.base

import androidx.lifecycle.ViewModel
import com.fact.api.AppAPIs
import com.fact.global.rxjava.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
abstract class BaseViewModel<N>(val appAPIs: AppAPIs, val schedulerProvider: SchedulerProvider) : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var navigator: N? = null

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
