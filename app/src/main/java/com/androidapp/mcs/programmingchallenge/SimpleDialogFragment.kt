package com.androidapp.mcs.programmingchallenge

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.androidapp.mcs.programmingchallenge.MainActivity.Companion.BUNDLE_KEY

class SimpleDialogFragment() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity!!.applicationContext)
        builder.setTitle("Random Jokes")

        val bundle = arguments
        builder.setMessage(bundle?.getString(BUNDLE_KEY))
        
        builder.setNeutralButton("Dismiss",DialogInterface.OnClickListener { dialog, which ->  returnToBaseActivity()})
        return builder.create()
    }

    private fun returnToBaseActivity() {

    }
}