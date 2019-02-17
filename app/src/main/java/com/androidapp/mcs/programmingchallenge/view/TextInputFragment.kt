package com.androidapp.mcs.programmingchallenge.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.androidapp.mcs.programmingchallenge.R

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class TextInputFragment : Fragment() {

    companion object {
        fun newInstance():TextInputFragment{
           return TextInputFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_text_input, container, false)

        return view
    }


}
