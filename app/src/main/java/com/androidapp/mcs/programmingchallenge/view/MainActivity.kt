package com.androidapp.mcs.programmingchallenge.view

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.R
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mViewModel = ViewModelProviders.of(this@MainActivity).get(BaseViewModel::class.java)

        mViewModel.getJokesFromVM()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe({
                showDialogBox(it)
            },
                {
                    Log.i("MainActivity", "Error Occured")
                })


        text_input.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.text_input, TextInputFragment.newInstance(), "Text_Input_Fragment")
                .commit()

        }
    }


    private fun showDialogBox(it2: RandomJokes) {

        val mJokes = it2.value.joke
        random_jokes.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Random Jokes")
            builder.setMessage(mJokes)
            builder.setNeutralButton("Dismiss") { dialogInterface, i ->
                Toast.makeText(applicationContext, "Dismiss", Toast.LENGTH_SHORT).show()
            }
            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()


        }
    }
}
//    private fun showDialogBox(it: String?) {
//        val simpleDialog = SimpleDialogFragment()
//        val fragmentManager = simpleDialog.fragmentManager
//        val bundle = Bundle()
//        bundle.putString(BUNDLE_KEY, it.toString())
//        simpleDialog.arguments
//        simpleDialog.show(fragmentManager,"Dialog Fragment")
//    }



