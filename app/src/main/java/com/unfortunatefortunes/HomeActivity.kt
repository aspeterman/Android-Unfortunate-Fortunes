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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
        private lateinit var mAuth: FirebaseAuth
        private lateinit var binding: ActivityMainBinding
        private lateinit var adapter: FortuneAdapter

        override fun onStart() {
            super.onStart()
        }
//        override fun onStart() {
//            super.onStart()
//            val user = mAuth.currentUser
//
//            if(user != null) {
//
//            } else {
//                startActivity(Intent(this, HomeActivity::class.java))
//            }
//        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_fortune)
            var mAuth = FirebaseAuth.getInstance();

            val query = FirebaseFirestore.getInstance().collection("fortunes")

//            val recyclerView: RecyclerView = findViewById(R.id.item_fortune_fortuneText_text_view)
//            adapter = FortuneAdapter(query)
//            recyclerView.adapter=adapter
// sign out a user

//            btnSignOut.setOnClickListener {
//                firebaseAuth.signOut()
//                startActivity(Intent(this, CreateAccountActivity::class.java))
//                toast("signed out")
//                finish()
//            }
        }


    }
