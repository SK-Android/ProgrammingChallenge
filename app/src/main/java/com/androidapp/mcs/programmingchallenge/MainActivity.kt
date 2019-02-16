package com.androidapp.mcs.programmingchallenge

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.MainActivity.Companion.BUNDLE_KEY
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        val BUNDLE_KEY = "bundle_key"
        lateinit var mRandomJokes:RandomJokes
    }
    init {
        var mViewModel: BaseViewModel? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mViewModel = ViewModelProviders.of(this@MainActivity).get(BaseViewModel::class.java)

        mViewModel.getJokes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe {
                {
                    mRandomJokes = it
                }
                {
                   Log.i("MainActivity","Error Occured")
                }

            }







        random_jokes.setOnClickListener { showDialogBox(mRandomJokes.value.joke) }

      }
    }


    private fun showDialogBox(it: String?) {
        val simpleDialog = SimpleDialogFragment()
        val fragmentManager = simpleDialog.fragmentManager
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY, it.toString())
        simpleDialog.arguments
        simpleDialog.show(fragmentManager,"Dialog Fragment")
    }



