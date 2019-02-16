package com.androidapp.mcs.programmingchallenge.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.androidapp.mcs.programmingchallenge.api.ApiClient
import com.androidapp.mcs.programmingchallenge.model.RandomJokes

class BaseViewModel(application: Application) : AndroidViewModel(application){

    private val mService  =  ApiClient()



    fun getData() : RandomJokes {

        return mService.loadData()

    }

}