package com.unfortunatefortunes.ui
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import com.unfortunatefortunes.model.Fortune
//import com.unfortunatefortunes.repository.FortuneRepository
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlin.random.Random
//
//@ExperimentalCoroutinesApi
//class FortuneViewModel(private val repository: FortuneRepository) : ViewModel() {
//
//    fun getAllFortunes() = repository.getAllFortunes()
//
//    fun addFortune(fortune: Fortune) = repository.addFortune(fortune)
//
//    fun getOne(): Query {
//        val fortuneRef = FirebaseFirestore.getInstance().collection("fortunes")
//        return fortuneRef.whereGreaterThanOrEqualTo("id", Random)
//            .limit(1)
//    }
//init {
//    getAllFortunes()
//    getOne()
//}
//
//}

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * ViewModel containing the app data and methods to process the data
 */
class FortuneViewModel : ViewModel(){


    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord

    // List of words used in the game
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    /*
    * Updates currentWord and currentScrambledWord with the next word.
    */
    private fun getNextWord() {
        currentWord = wordsList.random()

    }


}