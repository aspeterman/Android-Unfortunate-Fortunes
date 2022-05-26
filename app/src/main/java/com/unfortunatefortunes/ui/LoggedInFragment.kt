package com.unfortunatefortunes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.unfortunatefortunes.R


class LoggedInFragment : Fragment() {
    private var loggedInUserTextView: TextView? = null
    private var logOutButton: Button? = null
    private var loggedInViewModel: LoggedInViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel::class.java)
        loggedInViewModel!!.userLiveData.observe(
            this
        ) { firebaseUser ->
            if (firebaseUser != null) {
                loggedInUserTextView!!.text = "Logged In User: " + firebaseUser.email
                logOutButton!!.isEnabled = true
            } else {
                logOutButton!!.isEnabled = false
            }
        }
        loggedInViewModel!!.loggedOutLiveData.observe(
            this
        ) { loggedOut ->
            if (loggedOut) {
                Toast.makeText(context, "User Logged Out", Toast.LENGTH_SHORT).show()
                findNavController(view!!).navigate(R.id.action_loggedInFragment_to_loginRegisterFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_loggedin, container, false)
        loggedInUserTextView = view.findViewById(R.id.fragment_loggedin_loggedInUser)
        logOutButton = view.findViewById(R.id.fragment_loggedin_logOut)
        logOutButton.setOnClickListener(View.OnClickListener { loggedInViewModel!!.logOut() })
        return view
    }
}