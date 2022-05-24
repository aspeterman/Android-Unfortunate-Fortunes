package com.unfortunatefortunes.ui

import androidx.lifecycle.ViewModel
import com.unfortunatefortunes.model.Fortune
import com.unfortunatefortunes.repository.FortuneRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class FortuneViewModel(private val repository: FortuneRepository) : ViewModel() {

    fun getAllFortunes() = repository.getAllFortunes()

    fun addFortune(fortune: Fortune) = repository.addFortune(fortune)
}