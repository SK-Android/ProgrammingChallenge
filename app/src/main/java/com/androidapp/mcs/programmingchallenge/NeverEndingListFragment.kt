package com.androidapp.mcs.programmingchallenge


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidapp.mcs.programmingchallenge.model.Value
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_never_ending_list.*


class NeverEndingListFragment : Fragment() {

    var jokesList:List<Value>? = null

    companion object {
        fun newInstance():NeverEndingListFragment{
            return NeverEndingListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_never_ending_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(this@NeverEndingListFragment.context)
        getJokesList()
    }

    private fun getJokesList() {

        val api = ApiClient().jokesApi
        api.getJokesList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                    jokesList = it.value
                Log.i("NeverEndingListFragment","${jokesList?.size}")

            }, {
                //OnError
                Log.e("NeverEndingListFragment", it.message)
            }, {
                //onCompleted
                Log.i("NeverEndingListFragment", "Completed")

                recycler_view.adapter  = NeverEndingListAdapter(jokesList)
            })
    }


}
