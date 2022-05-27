package com.unfortunatefortunes

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.internal.ActivityLifecycleObserver.of
import com.google.android.gms.tasks.DuplicateTaskCompletionException.of
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.unfortunatefortunes.Extensions.toast
import com.unfortunatefortunes.FirebaseUtils.firebaseAuth
import com.unfortunatefortunes.adapter.FirestoreAdapter
import com.unfortunatefortunes.adapter.FortuneAdapter
import com.unfortunatefortunes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.EnumSet.of


    class HomeActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

// sign out a user

            btnSignOut.setOnClickListener {
                firebaseAuth.signOut()
                startActivity(Intent(this, CreateAccountActivity::class.java))
                toast("signed out")
                finish()
            }
        }
    }
