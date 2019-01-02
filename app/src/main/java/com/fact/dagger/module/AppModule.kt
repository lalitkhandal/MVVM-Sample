package com.fact.dagger.module

import android.app.Application
import android.content.Context
import com.fact.global.rxjava.AppSchedulerProvider
import com.fact.global.rxjava.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}