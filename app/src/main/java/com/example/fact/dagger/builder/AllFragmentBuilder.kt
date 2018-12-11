package com.example.fact.dagger.builder

import com.example.fact.view.home.fragment.HomeFragment
import com.example.fact.view.home.fragment.InfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
@Module
abstract class AllFragmentBuilder {
    @ContributesAndroidInjector(modules = [AllActivityModule::class])
    internal abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [AllActivityModule::class])
    internal abstract fun bindInfoFragment(): InfoFragment
}