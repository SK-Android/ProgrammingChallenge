package com.androidapp.mcs.programmingchallenge

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidapp.mcs.programmingchallenge.MainActivity.Companion.BUNDLE_KEY
import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.viewmodel.BaseViewModel


class MainActivity : AppCompatActivity() {

    companion object {
        val BUNDLE_KEY = "bundle_key"
    }
    init {
        var mViewModel: BaseViewModel? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mViewModel = ViewModelProviders.of(this@MainActivity).get(BaseViewModel::class.java)
        showDialogBox(mViewModel.getData())

      }
    }


    private fun showDialogBox(it: RandomJokes) {
        val simpleDialog = SimpleDialogFragment()
        var fragmentManager = simpleDialog.fragmentManager
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY, it.toString())
        simpleDialog.arguments
        simpleDialog.show(fragmentManager,"Dialog Fragment")


    }


    /**
     * When data is available provide it to the RecyclerVie
     * via RepoAdapter
     */
//    private fun setupAdapter(searchResponse: SearchResponse?) {
//        if (searchResponse != null) {
//            val adapter = RepoAdapter(searchResponse.repos)
//            repos.adapter = adapter
//        }




