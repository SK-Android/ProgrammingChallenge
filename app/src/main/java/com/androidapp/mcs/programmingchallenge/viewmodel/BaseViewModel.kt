package com.androidapp.mcs.programmingchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.androidapp.mcs.programmingchallenge.api.ApiClient
import com.androidapp.mcs.programmingchallenge.api.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import java.util.*

class BaseViewModel: ViewModel() {

//    private val mService  =  ApiClient()
//    fun getData() : ApiInterface {
//        Log.e("getAndroidData","yes")
//        return mService.gitApi
//    }

}