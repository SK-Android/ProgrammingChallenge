package com.androidapp.mcs.programmingchallenge


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import com.androidapp.mcs.programmingchallenge.model.Value
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_never_ending_list.*


class NeverEndingListFragment : Fragment() {

    var jokesList:List<Value>? = null
    lateinit var manager:LinearLayoutManager

    //Endless Scrolling
    var isScrolling = false
    var currentItems:Int = 0
    var totalItems:Int = 0
    var scrolledOutItems:Int = 0
    //

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
        manager = LinearLayoutManager(this@NeverEndingListFragment.context)
        recycler_view.layoutManager = manager

        //Endless Scrolling START
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            //Called when item scrolling completes
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrolledOutItems = manager.findFirstVisibleItemPosition()

                if(isScrolling && (currentItems + scrolledOutItems == totalItems)){

                    //Fetch data
                    isScrolling = false
                    progressBar.visibility = View.VISIBLE
                    getJokesList()
                }
            }

            //When item scrolling starts
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //If the state of the scrollbar has changed then it means user is scrolling
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }
        })
        //END

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

                progressBar.visibility = View.GONE
                recycler_view.adapter  = NeverEndingListAdapter(jokesList)
            })
    }


}
