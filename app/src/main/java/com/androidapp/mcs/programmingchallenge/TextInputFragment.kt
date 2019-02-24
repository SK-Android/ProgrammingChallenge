package com.androidapp.mcs.programmingchallenge


import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.service.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_text_input.*

class TextInputFragment : Fragment() {

    lateinit var name:String
    lateinit var firstName:String
    lateinit var lastName:String
    lateinit var mContext: Context
    var jokes: String = ""

    companion object {
        fun newInstance():TextInputFragment{
            return TextInputFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_text_input, container, false)
        mContext = view.context
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submit.setOnClickListener {
            name = editText.text.toString().trim() //editText.text doesn't return a string. It returns an object called editable

            if(name != null && !name.isEmpty() ){
                getFirstLastName()

            }else if (name == " " || name == ""){
                Toast.makeText(mContext,"Enter valid first name and last name",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(mContext,"Enter valid first name and last name",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFirstLastName() {

            firstName = name.substring(0, name.indexOf(" "))
            lastName = name.substring(name.indexOf(" ")+1)
            getJokes(firstName, lastName)
            Log.i("TextInputFragment","First name:"+firstName+"Last name:"+lastName)
    }

    private fun getJokes(firstName: String, lastName: String) {

        val api = ApiClient().jokesApi
        api.getCustomJokes(firstName,lastName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //onNext
                Log.i("TextInputFragment","$it")
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

    private fun showCustomJokesDialogBox(it: String) {

        Log.i("CustomJoke",""+it)
            val builder = AlertDialog.Builder(mContext)
            builder.setTitle("Custom Jokes")
            builder.setMessage(it)
            builder.setNeutralButton("Dismiss") { dialogInterface, i ->
                Toast.makeText(mContext, "Dismiss", Toast.LENGTH_SHORT).show()
                closefragment();

            }
            // Make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }

    private fun closefragment() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit();
    }
}



