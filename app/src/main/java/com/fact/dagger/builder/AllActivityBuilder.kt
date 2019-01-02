package com.fact.dagger.builder

import com.fact.dagger.module.AllActivityModule
import com.fact.view.home.HomeActivity
import com.fact.view.splash.SplashActivity
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

    @ContributesAndroidInjector(modules = [AllActivityModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity
}