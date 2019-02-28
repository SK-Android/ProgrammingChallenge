package com.androidapp.mcs.programmingchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var exclude: StringBuilder = StringBuilder("")
    var mRandomJoke: String? = ""

    companion object {
        const val RANDOM_JOKE: String = "Random_Joke"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random_jokes.setOnClickListener(this)
        text_input.setOnClickListener(this)
        never_ending_list.setOnClickListener(this)

        if (savedInstanceState != null && savedInstanceState.containsKey(RANDOM_JOKE)) {
            mRandomJoke = savedInstanceState.getString(RANDOM_JOKE)
            showRandomJokesDialogBox(mRandomJoke)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {

            //No Explicit Checkbox
            R.id.checkbox -> {
                checkbox.setOnClickListener {
                    if (checkbox.isChecked) {
                        exclude.append("explicit")
                    }
                }
            }

            //Random Jokes Button
            R.id.random_jokes -> {
                getRandomJokes()
            }

            //Custom Jokes Text Input Butoon
            R.id.text_input -> {

                val textInputDialog = TextInputDialog()
                val bundle = Bundle()
                textInputDialog.arguments.apply { bundle.putString("NoExplicit", exclude.toString()) }
                textInputDialog.show(supportFragmentManager, "TEXT_INPUT_DIALOG")

//                supportFragmentManager
//                    .beginTransaction()
//                    .add(R.id.container,TextInputFragment.newInstance(),"Text_Input_Fragment")
//                    .addToBackStack(null)
//                    .commit()

            }

            //Never Ending Jokes List
            R.id.never_ending_list -> {
                val bundle = Bundle()
                val neverEndingListFragment = NeverEndingListFragment()
                neverEndingListFragment.arguments.apply { bundle.putString("NoExplicit", exclude.toString()) }
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, neverEndingListFragment, "NeverEnding_List_Fragment")
                    .addToBackStack(null)
                    .commit()
            }
            else -> {
            }

        }
    }

    private fun getRandomJokes() {

        val api = ApiClient().jokesApi
        api.getJokesObservable(exclude.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                mRandomJoke = it.value.joke
                showRandomJokesDialogBox(mRandomJoke)

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
            Toast.makeText(applicationContext, "Dismissed", Toast.LENGTH_SHORT).show()
            if (dialogInterface != null) {
                dialogInterface.dismiss();
            }
        }
        val dialog: AlertDialog = builder.create()
            dialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(RANDOM_JOKE, mRandomJoke)
        super.onSaveInstanceState(outState)
    }


}


