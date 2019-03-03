package com.androidapp.mcs.programmingchallenge.service

import com.androidapp.mcs.programmingchallenge.model.JokeList
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.model.Value

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("jokes/random?escape=javascript")
    fun getJokesObservable(@Query("exclude") exclude:ArrayList<String>):Observable<RandomJokes>

    @GET("jokes/random?escape=javascript")
    fun getCustomJokes(@Query("firstName") firstName: String, @Query("lastName") lastName: String,
                       @Query("exclude") exclude:ArrayList<String>): Observable<RandomJokes>

    @GET("jokes/random/20?escape=javascript")
    fun getJokesList(@Query("exclude") exclude:ArrayList<String>):Observable<JokeList>

//    http://api.icndb.com/jokes/random?exclude=[nerdy]
//    http://api.icndb.com/jokes/random?exclude=[nerdy,explicit] ?exclude=[explicit]


}
