package com.example.fact.dagger.builder

import com.example.fact.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
@Module
abstract class AllActivityBuilder {
    @ContributesAndroidInjector(modules = [AllActivityModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity
}