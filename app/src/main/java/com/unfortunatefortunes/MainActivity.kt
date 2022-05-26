package com.unfortunatefortunes

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
import com.unfortunatefortunes.adapter.FortuneAdapter
import com.unfortunatefortunes.databinding.ActivityMainBinding
import com.unfortunatefortunes.ui.LoginViewModel
import com.unfortunatefortunes.ui.LoginViewModelFactory
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: FortuneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val query: Query = FirebaseFirestore.getInstance().collection("fortunes")

        val recyclerView: RecyclerView = findViewById(R.id.item_fortune)
        adapter = FortuneAdapter(query)
        recyclerView.adapter = adapter
    }




    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }
}