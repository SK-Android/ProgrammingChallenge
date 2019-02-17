package com.androidapp.mcs.programmingchallenge.service


import android.util.Log
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    val WRITE_TIMEOUT = 60
    val READ_TIMEOUT = 30
    val CONNECT_TIMEOUT = 30

companion object {
     var firstName:String = ""
     var lastName:String = ""
}


    //http://api.icndb.com/jokes/random

    private val API_URL = "http://api.icndb.com/"

    val jokesApi: ApiInterface by lazy { buildRetrofitWithInterceptors().create(ApiInterface::class.java) }

    private fun buildRetrofitWithInterceptors(): Retrofit {

        val okBuilder = OkHttpClient.Builder()
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.v(ApiClient::class.java.simpleName, message)
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okBuilder.addInterceptor(loggingInterceptor)

        val okHttpClient = okBuilder.build()

        val builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return builder.build()
    }

    fun getJokes(): Observable<RandomJokes> {
        return jokesApi.getRandomJokes()
    }
    fun getCustomJokes(firstName:String, lastName:String): Observable<RandomJokes> {
        return jokesApi.getCustomJokes(firstName,lastName)
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