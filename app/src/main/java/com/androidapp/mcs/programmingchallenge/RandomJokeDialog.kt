package com.androidapp.mcs.programmingchallenge

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class RandomJokeDialog : DialogFragment() {
    var temp: String? = ""
    var exclude: ArrayList<String> = ArrayList()
    var joke: String = ""

    companion object {
        const val JOKE: String = "Joke"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this!!.context!!)
        if (savedInstanceState != null && savedInstanceState.containsKey(JOKE)) {
            val mJoke = savedInstanceState.getString(JOKE)
            builder.setTitle("Random Joke")
                .setMessage(mJoke)
                .setNeutralButton("Dismiss", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this.context, "Dismissed", Toast.LENGTH_SHORT).show()
                    getDialog().dismiss()
                })
        } else {
            temp = arguments?.getString("EXCLUDE")
            Log.i("RandomJoke", temp.toString())
            exclude.add(temp.toString())
            getRandomJoke(exclude)
        }

        return builder.create()
    }


    private fun getRandomJoke(exclude: ArrayList<String>) {

        val api = ApiClient().jokesApi
        api.getJokesObservable(exclude)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                //val category = it.value.Categories.toString()
                joke = it.value.joke

                val builder = AlertDialog.Builder(this!!.context!!)
                builder.setTitle("Random Joke")
                    .setMessage(joke)
                    .setNeutralButton("Dismiss", DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this.context, "Dismissed", Toast.LENGTH_SHORT).show()
                        getDialog().dismiss()
                    })
                builder.create()
                val width = (resources.displayMetrics.widthPixels * 20).toInt()
                val height = (resources.displayMetrics.heightPixels * 20).toInt()
                val dialog: AlertDialog = builder.create()
                dialog.window.setLayout(width,height)
                dialog.show()


            }, {
                //OnError
                Log.e("RandomJokeDialog", it.message)

            }, {
                //onCompleted
                Log.i("RandomJokeDialog", "Completed $joke")

            })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(JOKE, joke)
    }

    override fun onDestroyView() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss()
        }
        super.onDestroyView()
    }
//
//    override fun onConfigurationChanged(newConfig: Configuration?) {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss()
//        }
//        super.onConfigurationChanged(newConfig)
//    }


}