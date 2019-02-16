package com.androidapp.mcs.programmingchallenge.api

import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.model.Value
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("jokes/random")
    fun getRandomJokes(): Observable<RandomJokes>

}
//} @GET("/posts")
//
//fun getPosts(): Observable<List<Post>>