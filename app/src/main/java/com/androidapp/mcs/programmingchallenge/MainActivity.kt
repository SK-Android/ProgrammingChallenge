package com.androidapp.mcs.programmingchallenge

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
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
            .subscribe({ result ->
                showDialogBox(result)
            },
                { error ->
                    Log.i("MainActivity", "Error Occured")
                })
    }


    private fun showDialogBox(it2: RandomJokes) {

        Toast.makeText(applicationContext, "Recieved", Toast.LENGTH_SHORT)

        random_jokes.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Random Jokes")
            builder.setMessage(it2.value.joke)
            builder.setNeutralButton("Dismiss") { dialogInterface, i ->
                Toast.makeText(applicationContext, "Dismiss", Toast.LENGTH_SHORT).show()
            }


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



