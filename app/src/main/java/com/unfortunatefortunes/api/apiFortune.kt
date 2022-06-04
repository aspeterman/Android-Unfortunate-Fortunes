package com.unfortunatefortunes.api

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.lifecycle.liveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unfortunatefortunes.Constants
import com.unfortunatefortunes.model.Fortune
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper.cancel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class apiFortune {

    val db = Firebase.firestore


    fun getFortunes() {
//        val db = FirebaseFirestore.getInstance()
        db.collection("fortunes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    fun getGood() {
        val TAG = "MainActivity"
        val docReference = db.collection(Constants.COLLECTION_FORTUNE)

        docReference.whereEqualTo("rating", "GOOD").get().addOnSuccessListener {querySnapshot ->
            for (doc in querySnapshot) {
                Log.d(TAG, "id: ${doc.id} fortuneText: ${doc.data.getValue("fortuneText")}")
            }
        }
    }


//    fun getPosts(userId: String): Flow<List<Fortune>> {
//        val db = FirebaseFirestore.getInstance()
//        return callbackFlow {
//            val listenerRegistration = db.collection("users")
//                .document(userId)
//                .collection("posts")
//                .addSnapshotListener { querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
//                    if (firebaseFirestoreException != null) {
//                        cancel(
//                            message = "Error fetching posts",
//                            cause = firebaseFirestoreException
//                        )
//                        return@addSnapshotListener
//                    }
//                    val map = querySnapshot.documents.
//                        .mapNotNull { it.toPost() }
//                    offer(map)
//                }
//            awaitClose {
//                Log.d(TAG, "Cancelling posts listener")
//                listenerRegistration.remove()
//            }
//        }
//    }
}