package com.androidapp.mcs.programmingchallenge

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidapp.mcs.programmingchallenge.model.Value
import com.androidapp.mcs.programmingchallenge.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val BUNDLE_KEY = "bundle_key"
        var mViewModel: BaseViewModel? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViewModel()
        random_jokes.setOnClickListener {
            val mViewModel = ViewModelProviders.of(this@MainActivity).get(BaseViewModel::class.java)
            mViewModel.getData().getRandomJokes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(IoScheduler())
                .subscribe({
                    showDialogBox(it)

                })
        }

//        input_text.setOnClickListener {
//            val mViewModel = ViewModelProviders.of(this@MainActivity).get(BaseViewModel::class.java)
//            mViewModel.getData().getRandomJokes()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(IoScheduler())
//                .subscribe({
//
//                })
//        }

//        never_ending_list.setOnClickListener {
//            val mViewModel = ViewModelProviders.of(this@MainActivity).get(BaseViewModel::class.java)
//            mViewModel.getData().getRandomJokes()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(IoScheduler())
//                .subscribe({
//
//                })
//        }
    }

    private fun initViewModel() {
        mViewModel = ViewModelProviders.of(this)
            .get(MainViewModel)
    }

    private fun showDialogBox(it: Value) {
        val simpleDialog = SimpleDialogFragment()
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY, it.toString())
        simpleDialog.arguments
        simpleDialog.show(getSupportFragmentManager(),"Dialog Fragment")

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

    }


