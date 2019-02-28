package com.androidapp.mcs.programmingchallenge.service

import com.androidapp.mcs.programmingchallenge.model.JokeList
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.model.Value

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
//    companion object {
////
////        const val randomJokes = "jokes/random?escape=javascript"
////        const val customJoke = "jokes/random?escape=javascript"
////        const val jokeList = "jokes/random/20?escape=javascript"
////        fun modifyApi(api:String){
////            randomJokes
////        }
//    }


    @GET("jokes/random?escape=javascript")
    fun getJokesObservable(@Query("exclude") exclude:String):Observable<RandomJokes>

    @GET("jokes/random?escape=javascript")
    fun getCustomJokes(@Query("firstName") firstName: String, @Query("lastName") lastName: String,
                       @Query("exclude") exclude:String): Observable<RandomJokes>

    @GET("jokes/random/20?escape=javascript")
    fun getJokesList(@Query("exclude") exclude:String):Observable<JokeList>
    /*
    http://api.icndb.com/jokes/random?exclude=[nerdy]
    http://api.icndb.com/jokes/random?exclude=[nerdy,explicit] ?exclude=[explicit]
    */
    //


}
