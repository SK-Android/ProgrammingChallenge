package com.androidapp.mcs.programmingchallenge.api

import com.androidapp.mcs.programmingchallenge.model.RandomJokes

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("jokes/random")
    fun getRandomJokes(): Observable<RandomJokes>

}
