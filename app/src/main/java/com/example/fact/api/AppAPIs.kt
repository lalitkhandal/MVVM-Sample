package com.example.fact.api

import com.example.fact.model.FactResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


/**
 * Created by Lalit Khandelwal on 11, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
interface AppAPIs {
    @Headers("Content-Type: application/json")
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFactData(): Observable<FactResponse>
}