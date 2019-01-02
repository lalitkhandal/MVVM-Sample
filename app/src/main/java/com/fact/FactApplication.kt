package com.fact

import android.app.Activity
import android.app.Application
import com.fact.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Lalit Khandelwal on 11, December, 2018
 * lalitkhandelwal99@gmail.com
 */
class FactApplication : Application(), HasActivityInjector {

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appBuilder(this).buildAppComponent().inject(this)
    }
}