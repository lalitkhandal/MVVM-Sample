package com.example.fact.dagger.builder

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
    internal fun provideSplashViewModel(schedulerProvider: SchedulerProvider): SplashViewModel {
        return SplashViewModel(schedulerProvider)
    }

    @Provides
    internal fun provideHomeViewModel(schedulerProvider: SchedulerProvider): HomeViewModel {
        return HomeViewModel(schedulerProvider)
    }
}