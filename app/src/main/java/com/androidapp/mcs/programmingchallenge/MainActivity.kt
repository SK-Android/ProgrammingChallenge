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
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), View.OnClickListener {


    var exclude: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random_jokes.setOnClickListener(this)
        text_input.setOnClickListener(this)
        never_ending_list.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            //No Explicit Checkbox
            R.id.checkbox -> {
                checkbox.setOnClickListener {
                    if (checkbox.isChecked) {
                        exclude.add("explicit")
                    }
                }
            }

            //Random Jokes Button
            R.id.random_jokes -> {
                val randomJokeDialog = RandomJokeDialog()
                val bundle = Bundle()
                randomJokeDialog.arguments.apply { bundle.putString("EXCLUDE", exclude.toString()) }
                randomJokeDialog.show(supportFragmentManager, "RANDOM_JOKE_DIALOG")
            }

            //Custom Jokes Text Input Butoon
            R.id.text_input -> {
                val textInputDialog = TextInputDialog()
                val bundle = Bundle()
                textInputDialog.arguments.apply { bundle.putStringArrayList("NoExplicit", exclude) }
                textInputDialog.show(supportFragmentManager, "TEXT_INPUT_DIALOG")
            }

            //Never Ending Jokes List
            R.id.never_ending_list -> {
                val bundle = Bundle()
                val neverEndingListFragment = NeverEndingListFragment()
                neverEndingListFragment.arguments.apply { bundle.putStringArrayList("NoExplicit", exclude) }
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
}





