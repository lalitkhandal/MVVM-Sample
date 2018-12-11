package com.example.fact.viewmodel

import com.example.fact.global.AppConstants
import com.example.fact.global.rxjava.SchedulerProvider
import com.example.fact.navigator.ActivityNavigator
import com.example.fact.view.base.BaseViewModel
import com.example.fact.view.home.HomeActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Lalit Khandelwal on 11, December, 2018
 * lalitkhandelwal99@gmail.com
 */
class SplashViewModel(schedulerProvider: SchedulerProvider) : BaseViewModel<ActivityNavigator>(schedulerProvider) {
    init {
        delayScreen()
    }

    private fun delayScreen() {
        Single.fromCallable {}
            .subscribeOn(schedulerProvider.io())
            .delay(AppConstants.SPLASH_DELAY.toLong(), TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .doOnSuccess { navigator?.startActivity(HomeActivity::class.java) }
            .subscribe()
    }
}