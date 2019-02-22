package com.androidapp.mcs.programmingchallenge

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.R
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = ApiClient().jokesApi
        api.getJokesObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                showRandomJokesDialogBox(it)

            }, {
                //OnError
                Log.e("MainActivity", it.message)
            }, {
                //onCompleted
                Log.i("MainActivity", "Completed")
            })


        random_jokes.setOnClickListener {

        }

    }

    private fun showRandomJokesDialogBox(it: RandomJokes?) {

        val mJokes = it?.value?.joke
        random_jokes.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Random Jokes")
            builder.setMessage(mJokes)
            builder.setNeutralButton("Dismiss") { dialogInterface, i ->
                Toast.makeText(applicationContext, "Dismiss", Toast.LENGTH_SHORT).show()
            }
            // Make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
    }
}



