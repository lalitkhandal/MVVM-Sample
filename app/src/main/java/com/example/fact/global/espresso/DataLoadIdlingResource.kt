package com.example.fact.global.espresso

import androidx.test.espresso.IdlingResource

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Lalit Khandelwal on 12, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
class DataLoadIdlingResource : IdlingResource {

    private var mIsIdleNow = AtomicBoolean(true)
    @Volatile
    private var mCallback: IdlingResource.ResourceCallback? = null


    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return mIsIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        mCallback = callback
    }

    /**
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    fun setIdleState(isIdleNow: Boolean) {
        println("IS IDLE NOW: $isIdleNow")
        if (isIdleNow) {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        println("IS IDLE NOW: $isIdleNow")
        mIsIdleNow.set(isIdleNow)
        if (isIdleNow && mCallback != null) {
            mCallback!!.onTransitionToIdle()
        }
    }
}