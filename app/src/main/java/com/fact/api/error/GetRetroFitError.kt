package com.fact.api.error

import com.fact.navigator.ErrorNavigator
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
class GetRetroFitError(private val errorNavigator: ErrorNavigator?, throwable: Throwable) {

    init {
        checkError(throwable)
    }

    /**
     * Detect server error and message
     */
    private fun checkError(t: Throwable) {
        when (t) {
            is HttpException -> {
                val responseBody = t.response().errorBody()
                try {
                    val jsonObject = JSONObject(responseBody?.string())
                    val statusCode = jsonObject.getInt("statusCode")
                    var message = ""
                    if (jsonObject.has("message"))
                        message = jsonObject.getString("message")

                    errorNavigator?.onUnknownErrorCode(statusCode, message)
                } catch (e: Exception) {
                    e.message?.let { errorNavigator?.onUnknownError(it) }
                }
            }
            is SocketTimeoutException -> errorNavigator?.onTimeout()
            is IOException -> errorNavigator?.onNetworkError()
            else -> t.message?.let { errorNavigator?.onUnknownError(it) }
        }
    }
}