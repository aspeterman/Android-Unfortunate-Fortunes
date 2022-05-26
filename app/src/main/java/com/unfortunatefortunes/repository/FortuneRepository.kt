package com.unfortunatefortunes.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.unfortunatefortunes.Constants
import com.unfortunatefortunes.State
import com.unfortunatefortunes.model.Fortune
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

@ExperimentalCoroutinesApi
class FortuneRepository {
    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()
    var user = FirebaseAuth.getInstance().currentUser
    private val mFortunesCollection =
        FirebaseFirestore.getInstance().collection(Constants.COLLECTION_FORTUNE)

//    fun saveFortune(fortune: Fortune): Task<Void> {
//        //var
//        var documentReference = firestoreDB.collection("users").document(user!!.email.toString())
//            .collection("genders").document(fortune?.gender)
//        return documentReference.set(fortune)
//    }

    /**
     * Returns Flow of [State] which retrieves all posts from cloud firestore collection.
     */
    fun getAllFortunes() = flow<State<List<Fortune>>> {

        // Emit loading state
        emit(State.loading())

        val snapshot = mFortunesCollection.get().await()
        val posts = snapshot.toObjects(Fortune::class.java)

        // Emit success state with data
        emit(State.success(posts))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    /**
     * Adds post [post] into the cloud firestore collection.
     * @return The Flow of [State] which will store state of current action.
     */
    fun addFortune(post: Fortune) = flow<State<DocumentReference>> {

        // Emit loading state
        emit(State.loading())

        val postRef = mFortunesCollection.add(post).await()

        // Emit success state with post reference
        emit(State.success(postRef))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}