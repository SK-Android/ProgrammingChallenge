package com.androidapp.mcs.programmingchallenge.view


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androidapp.mcs.programmingchallenge.R


import com.androidapp.mcs.programmingchallenge.model.RandomJokes
import com.androidapp.mcs.programmingchallenge.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_text_input.*





class TextInputFragment : Fragment() {

    companion object {
        fun newInstance():TextInputFragment{
           return TextInputFragment()


        }

        lateinit var name:String
        lateinit var firstName:String
        lateinit var lastName:String
        lateinit var mContext:Context
        lateinit var jokes:RandomJokes
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_text_input, container, false)
        mContext = view.context
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit.setOnClickListener {

            getFirstLastName()

        }
    }

    private fun getFirstLastName() {
        name = editText.text.toString()              //editText.text doesn't return a string. It returns an object called editable

        firstName = name.substring(0, name.indexOf(""))
        lastName = name.substring(name.indexOf("")+1)

//        Toast.makeText(context, ""+firstName+"\n"+lastName,Toast.LENGTH_SHORT).show()
         getJokes(firstName, lastName)

    }


    private fun getJokes(firstName: String, lastName: String) {
        val mViewModel = ViewModelProviders.of(this@TextInputFragment).get(BaseViewModel::class.java)
        mViewModel.getCustomJokesFromVM(firstName,lastName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe({
//                jokes = it

            },
                {
                    Log.i("TextInputFragment", "Error Occured")
                })
    }

    public interface FragmentListener {
        fun onFragmentFinish(jokes: RandomJokes)

    }


    //Displaying Jokes using Alert Dialog

//    private fun showJokesDialogBox(it: RandomJokes) {
//        if (it != null){
//        var mJokes:String = it.value.joke
//        random_jokes.setOnClickListener {
//            val mBuilder = AlertDialog.Builder(mContext)              //This line of code doesn't work
//            mBuilder.setTitle("Custom Jokes")
//            mBuilder.setMessage(mJokes)
//            mBuilder.setNeutralButton("Dismiss") { dialogInterface, i ->
//                Toast.makeText(context, "Dismiss", Toast.LENGTH_SHORT).show()
//            }
//            // Finally, make the alert dialog using builder
//            val dialog: AlertDialog = mBuilder.create()
//
//            // Display the alert dialog on app interface
//            dialog.show()
//        }
//
//        }
//    }


}
