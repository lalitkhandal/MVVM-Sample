package com.fact.dagger.module

import com.fact.api.AppAPIs
import com.fact.global.rxjava.SchedulerProvider
import com.fact.viewmodel.HomeViewModel
import com.fact.viewmodel.SplashViewModel
import dagger.Module
import dagger.Provides


/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
@Module
class AllActivityModule {

    @Provides
    internal fun provideSplashViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider): SplashViewModel {
        return SplashViewModel(appAPIs, schedulerProvider)
    }

    @Provides
    internal fun provideHomeViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider): HomeViewModel {
        return HomeViewModel(appAPIs, schedulerProvider)
    }
}