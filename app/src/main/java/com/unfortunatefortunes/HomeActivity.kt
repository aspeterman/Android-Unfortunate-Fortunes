package com.unfortunatefortunes

//import android.content.Intent
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.ViewModelProviders
//import androidx.lifecycle.ViewModelStore
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.gms.common.api.internal.ActivityLifecycleObserver.of
//import com.google.android.gms.tasks.DuplicateTaskCompletionException.of
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import com.unfortunatefortunes.Extensions.toast
//import com.unfortunatefortunes.FirebaseUtils.firebaseAuth
//import com.unfortunatefortunes.adapter.FirestoreAdapter
//import com.unfortunatefortunes.adapter.FortuneAdapter
//import com.unfortunatefortunes.databinding.ActivityMainBinding
//import com.unfortunatefortunes.ui.FortuneFragment
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_main.btnSignOut
//import kotlinx.android.synthetic.main.fragment_fortune.*
//import java.util.EnumSet.of
//
//
//    class HomeActivity : AppCompatActivity() {
//         lateinit var mAuth: FirebaseAuth
//         lateinit var binding: ActivityMainBinding
//         lateinit var adapter: FortuneAdapter
//
//        override fun onStart() {
//            super.onStart()
//
//        }
////        override fun onStart() {
////            super.onStart()
////    val currentUser = mAuth.currentUser
////            val user = currentUser
////
////            if(user != null) {
////
////            } else {
////                startActivity(Intent(this, HomeActivity::class.java))
////            }
////        }
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.fragment_fortune)
//
//            val query = FirebaseFirestore.getInstance().collection("fortunes")
//
////            val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
//            adapter = FortuneAdapter(query)
////            recyclerView.adapter=adapter
//// sign out a user
//
//            btnSignOut.setOnClickListener {
//                firebaseAuth.signOut()
//                startActivity(Intent(this, CreateAccountActivity::class.java))
//                toast("signed out")
//                finish()
//            }
//
//        }
//
//
//    }
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unfortunatefortunes.Extensions.toast
import com.unfortunatefortunes.FirebaseUtils.firebaseAuth
import com.unfortunatefortunes.adapter.FortuneAdapter
import com.unfortunatefortunes.databinding.ActivityMainBinding
import com.unfortunatefortunes.model.Category
import com.unfortunatefortunes.model.Fortune
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fortune.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {
             lateinit var mAuth: FirebaseAuth
         lateinit var binding: ActivityMainBinding
    private lateinit var fortuneAdapter : FortuneAdapter

    private val todosCollectionReference = Firebase.firestore.collection("fortunes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //if there is previous data, it will show that in the fortuneAdapter, otherwise, it'll show an empty list
        fortuneAdapter = FortuneAdapter(mutableListOf())
        retrieveTodosDatabase()

        rvFortuneItems.adapter = fortuneAdapter
        rvFortuneItems.layoutManager = LinearLayoutManager(this)

        //on click listeners for our two buttons
        btnAddFortune.setOnClickListener {
            val fortuneText = etFortuneText.text.toString()
            if (fortuneText.isNotEmpty()) {
                val fortune =
                    Fortune(fortuneText, gender = String(), rating = Category.BAD, adult = true)
                fortuneAdapter.addFortune(fortune)
                etFortuneText.text.clear()
                //adding to the database
                addTodoDatabase(fortune)
            }
        }
        btnShare.setOnClickListener {
            startActivity(share)

        }
        btnSignOut.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, CreateAccountActivity::class.java))
            toast("signed out")
            finish()
        }

    }
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "https://developer.android.com/training/sharing/")

            // (Optional) Here we're setting the title of the content
            putExtra(Intent.EXTRA_TITLE, "Introducing content previews")

            // (Optional) Here we're passing a content URI to an image to be displayed
//            data = contentUri
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)

//


    private fun addTodoDatabase(fortune : Fortune) = CoroutineScope(Dispatchers.IO).launch {
        try {
            todosCollectionReference.add(fortune).await()
            //this code will only run after the add function is finished because of the await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@HomeActivity, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
        } catch (e : Exception) {
            //switching to the main context to show the error in a toast
            withContext(Dispatchers.Main) {
                Toast.makeText(this@HomeActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun retrieveTodosDatabase() = CoroutineScope(Dispatchers.Main).launch {
        try {
            val querySnapshot = todosCollectionReference.get().await()
            //this code will only run after the add function is finished because of the await()
            val todoList = mutableListOf<Fortune>()
            for (document in querySnapshot.documents) {
                val todo = document.toObject(Fortune::class.java)
                if (todo != null) {
                    todoList.add(todo)
                    fortuneAdapter.addFortune(todo)
                }
            }
        } catch (e : Exception) {
            Toast.makeText(this@HomeActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteTodoDatabase() = CoroutineScope(Dispatchers.IO).launch {
        try {
            //creating a query that'll return all the tasks that have "checked" equal to true
            val tasksQuery = todosCollectionReference.whereEqualTo("checked", true).get().await()

            //this code will only run after the add function is finished because of the await()
            if (tasksQuery.documents.isNotEmpty()) {
                for (document in tasksQuery) {
                    try {
                        todosCollectionReference.document(document.id).delete().await()
                    } catch (e : Exception) {
                        //switching to the main context to show the error in a toast
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@HomeActivity, e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(this@HomeActivity, "Successfully deleted data", Toast.LENGTH_SHORT).show()
            }
        } catch (e : Exception) {
            //switching to the main context to show the error in a toast
            withContext(Dispatchers.Main) {
                Toast.makeText(this@HomeActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }



}