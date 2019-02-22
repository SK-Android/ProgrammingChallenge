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
import java.util.*


class NeverEndingListFragment : Fragment() {


    lateinit var jokesArray:Value

    companion object {
        fun newInstance():TextInputFragment{
            return TextInputFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_never_ending_list, container, false)
        recycler_view.layoutManager = LinearLayoutManager(this@NeverEndingListFragment.context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJokesList()
    }

    private fun getJokesList() {

        val api = ApiClient().jokesApi
        api.getJokesList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                jokesArray = it
                Log.i("NeverEndingListFragment","$it")

            }, {
                //OnError
                Log.e("NeverEndingListFragment", it.message)
            }, {
                //onCompleted
                Log.i("NeverEndingListFragment", "Completed")

                val arrayList = ArrayList<Value>(Arrays.asList(jokesArray))

                recycler_view.adapter  = NeverEndingListAdapter(arrayList)
            })
    }


}
