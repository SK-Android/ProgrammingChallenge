package com.androidapp.mcs.programmingchallenge.service

import com.androidapp.mcs.programmingchallenge.model.RandomJokes

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("jokes/random")
    fun getRandomJokes(): Observable<RandomJokes>

    @GET("jokes/random")
    fun getCustomJokes(@Query("firstName") firstName: String, @Query("lastName") lastName: String): Observable<RandomJokes>
}
//Model.Result