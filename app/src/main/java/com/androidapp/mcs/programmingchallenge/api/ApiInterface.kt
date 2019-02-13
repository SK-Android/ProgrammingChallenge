package com.androidapp.mcs.programmingchallenge.api

import com.androidapp.mcs.programmingchallenge.model.Value
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("jokes/random")
    fun getRandomJokes(): Call<Value>

}
//} @GET("/posts")
//
//fun getPosts(): Observable<List<Post>>