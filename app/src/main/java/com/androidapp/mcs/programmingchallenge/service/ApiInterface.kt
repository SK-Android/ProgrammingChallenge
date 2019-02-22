package com.androidapp.mcs.programmingchallenge.service

import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.model.Value

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("jokes/random")
    fun getJokesObservable():Observable<RandomJokes>

    @GET("jokes/random")
    fun getCustomJokes(@Query("firstName") firstName: String, @Query("lastName") lastName: String): Observable<RandomJokes>

    @GET("jokes/random/20")
    fun getJokesList():Observable<Value>
}
