package com.androidapp.mcs.programmingchallenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidapp.mcs.programmingchallenge.model.Value

class NeverEndingListAdapter(val jokes: List<Value>?): RecyclerView.Adapter<NeverEndingListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val itemView = layoutInflater.inflate(R.layout.fragment_list_item, p0, false)
        return  CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jokes!!.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.mJokes.text = jokes?.get(p1)?.joke.toString()
    }

    class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val mJokes:TextView = itemView.findViewById(R.id.never_ending_list_joke)
    }
}