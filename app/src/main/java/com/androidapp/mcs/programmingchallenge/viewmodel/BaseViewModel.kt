package com.androidapp.mcs.programmingchallenge.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.androidapp.mcs.programmingchallenge.api.ApiClient
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import io.reactivex.Observable

class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun getJokesFromVM(): Observable<RandomJokes> {
        return ApiClient().getJokes()
    }

}
