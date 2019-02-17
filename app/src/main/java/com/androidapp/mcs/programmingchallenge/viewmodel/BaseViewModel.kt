package com.androidapp.mcs.programmingchallenge.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import io.reactivex.Observable

class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun getRandomJokesFromVM(): Observable<RandomJokes> {
        return ApiClient().getJokes()
    }

    fun getCustomJokesFromVM( firstName:String, lastName:String): Observable<RandomJokes> {
        return ApiClient().getCustomJokes(firstName,lastName)
    }
}
