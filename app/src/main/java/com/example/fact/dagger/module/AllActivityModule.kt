package com.example.fact.dagger.module

import com.example.fact.api.AppAPIs
import com.example.fact.global.rxjava.SchedulerProvider
import com.example.fact.viewmodel.HomeViewModel
import com.example.fact.viewmodel.SplashViewModel
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