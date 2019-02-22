package com.androidapp.mcs.programmingchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random_jokes.setOnClickListener(this)
        text_input.setOnClickListener(this)
        never_ending_list.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){

            R.id.random_jokes ->{
                getRandomJokes()
            }
            R.id.text_input ->{
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container,TextInputFragment.newInstance(),"Text_Input_Fragment")
                    .commit()
            }
            R.id.never_ending_list ->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container,NeverEndingListFragment.newInstance(),"NeverEnding_List_Fragment")
                    .commit()
            }
            else->{}

        }
    }



    private fun getRandomJokes() {

            val api = ApiClient().jokesApi
            api.getJokesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    //onNext
                    val mJokes = it?.value?.joke
                    showRandomJokesDialogBox(mJokes)

                }, {
                    //OnError
                    Log.e("MainActivity", it.message)
                }, {
                    //onCompleted
                    Log.i("MainActivity", "Completed")
                })
    }

    private fun showRandomJokesDialogBox(it: String?) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Random Jokes")
        builder.setMessage(it)
        builder.setNeutralButton("Dismiss") { dialogInterface, i ->
            Toast.makeText(applicationContext, "Dismiss", Toast.LENGTH_SHORT).show()
        }
// Make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

// Display the alert dialog on app interface
        dialog.show()

    }
}


