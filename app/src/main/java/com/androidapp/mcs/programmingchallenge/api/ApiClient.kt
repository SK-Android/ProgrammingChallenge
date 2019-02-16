package com.androidapp.mcs.programmingchallenge.api


import android.util.Log
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class ApiClient {

    //http://api.icndb.com/jokes/random

    private val GIT_API_URL = "https://api.github.com/"

companion object {
    lateinit var jokes: RandomJokes
}
    val jokesApi: ApiInterface by lazy { buildRetrofitWithInterceptors().create(ApiInterface::class.java) }

    private fun buildRetrofitWithInterceptors(): Retrofit {

        val builder = Retrofit.Builder()
            .baseUrl(GIT_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return builder.build()
    }

    fun loadData(): RandomJokes {

            jokesApi.getRandomJokes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe({
                jokes = it
            })
        return jokes
    }



}

/*
@Module
object ApiClient {

    //http://api.icndb.com/jokes/random

        @Provides
        @Reusable
        @JvmStatic
        internal fun provideApi(retrofit: Retrofit): ApiInterface {         // Retrofit object is used to instantiate the service
            return retrofit.create(ApiInterface::class.java)                //  and returns the Post service implementation.
        }




        @Provides
        @Reusable
        @JvmStatic
        internal fun provideRetrofitInterface(): Retrofit {                  //Provides the Retrofit object.
            return Retrofit.Builder()
                .baseUrl("http://api.icndb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
 */