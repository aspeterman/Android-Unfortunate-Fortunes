package com.unfortunatefortunes.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.unfortunatefortunes.model.Fortune
import com.unfortunatefortunes.repository.FortuneRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.random.Random

@ExperimentalCoroutinesApi
class FortuneViewModel(private val repository: FortuneRepository) : ViewModel() {

    fun getAllFortunes() = repository.getAllFortunes()

    fun addFortune(fortune: Fortune) = repository.addFortune(fortune)

    fun getOne(): Query {
        val fortuneRef = FirebaseFirestore.getInstance().collection("fortunes")
        return fortuneRef.whereGreaterThanOrEqualTo("id", Random)
            .limit(1)
    }
init {
    getAllFortunes()
}

}