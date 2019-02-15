package com.androidapp.mcs.programmingchallenge.api


import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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





}