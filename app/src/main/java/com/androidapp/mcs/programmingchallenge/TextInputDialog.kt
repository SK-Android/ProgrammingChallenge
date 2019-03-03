package com.androidapp.mcs.programmingchallenge

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_text_input.*
import java.util.ArrayList

class TextInputDialog : DialogFragment() {


    lateinit var name: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var mContext: Context
    var jokes: String = ""
    var exclude:ArrayList<String> = ArrayList<String>()
    lateinit var dialog: AlertDialog


    companion object {
        const val CUSTOM_JOKE: String = "Custom_Joke"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_text_input, container, false)
        mContext = view.context
        arguments?.let {
            exclude = it.getStringArrayList("NoExplicit")
        }
        return view
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(CUSTOM_JOKE)) {
            val joke = savedInstanceState.getString(CUSTOM_JOKE)
            showCustomJokesDialogBox(joke)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit.setOnClickListener {
            name = editText.text.toString().trim() //editText.text doesn't return a string. It returns an object called editable

            if (!name.isEmpty()) {
                getFirstLastName()

            } else if (name == " " || name == "") {
                Toast.makeText(mContext, "Enter valid first name and last name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(mContext, "Enter valid first name and last name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFirstLastName() {

        firstName = name.substring(0, name.indexOf(" "))
        lastName = name.substring(name.indexOf(" ") + 1)
        getJokes(firstName, lastName)
        Log.i("TextInputFragment", "First name:" + firstName + "Last name:" + lastName)
    }

    private fun getJokes(firstName: String, lastName: String) {
        val api = ApiClient().jokesApi
        api.getCustomJokes(firstName, lastName, exclude)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                Log.i("TextInputFragment", "$it")
                jokes = it.value.joke
            }, {
                //OnError
                Log.e("TextInputFragment", it.message)
            }, {
                //onCompleted
                Log.i("TextInputFragment", "Completed")
                showCustomJokesDialogBox(jokes)
            })
    }

    private fun showCustomJokesDialogBox(jokes: String) {
        Log.i("CustomJoke", "" + jokes)
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle("Custom Jokes")
        builder.setMessage(jokes)
        builder.setNeutralButton("Dismiss") { dialogInterface, i ->
            Toast.makeText(mContext, "Dismiss", Toast.LENGTH_SHORT).show()
        }
        // Make the alert dialog using builder
        dialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CUSTOM_JOKE, jokes)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        dialog.dismiss()
        super.onDestroy()
    }

}