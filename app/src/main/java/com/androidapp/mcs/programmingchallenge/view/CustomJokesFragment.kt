package com.androidapp.mcs.programmingchallenge.view


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.androidapp.mcs.programmingchallenge.R
import com.androidapp.mcs.programmingchallenge.model.RandomJokes

class CustomJokesFragment : DialogFragment() {

    companion object {
        fun newInstance(it: RandomJokes?): CustomJokesFragment {
            return CustomJokesFragment()

        }



        fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            val view:View =  inflater.inflate(R.layout.fragment_custom_jokes, container, false)

            return view
        }


    }}
