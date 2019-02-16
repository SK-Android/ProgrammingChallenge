package com.androidapp.mcs.programmingchallenge.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.androidapp.mcs.programmingchallenge.api.ApiClient
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler




class BaseViewModel(application: Application) : AndroidViewModel(application) {

    fun getJokes():Observable<RandomJokes>{
        return ApiClient().getJokes()
    }

}



//
//    fun getObserver(): DisposableObserver<MovieResponse> {
//
//        return object : DisposableObserver<MovieResponse>() {
//
//
//            override fun onNext(@NonNull movieResponse: MovieResponse) {
//
//                Log.d(TAG, "OnNext" + movieResponse.getTotalResults())
//
//                mvi.displayMovies(movieResponse)
//
//            }
//
//
//            override fun onError(@NonNull e: Throwable) {
//
//                Log.d(TAG, "Error$e")
//
//                e.printStackTrace()
//
//                mvi.displayError("Error fetching Movie Data")
//
//            }
//
//
//            override fun onComplete() {
//
//                Log.d(TAG, "Completed")
//
//            }
//
//        }
//
//    }
//    fun getData() : RandomJokes? {
//
//            mService.getRandomJokes()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(IoScheduler())
//                .subscribe({
//                  randomJokes = it
//                })
//            return randomJokes
//
//
//    }

