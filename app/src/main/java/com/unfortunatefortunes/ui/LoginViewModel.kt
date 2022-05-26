package com.unfortunatefortunes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.unfortunatefortunes.repository.AuthAppRepository


class LoggedInViewModel(application: Application) : AndroidViewModel(application) {
    private val authAppRepository: AuthAppRepository
    val userLiveData: MutableLiveData<FirebaseUser?>
    val loggedOutLiveData: MutableLiveData<Boolean>

    fun logOut() {
        authAppRepository.logOut()
    }

    init {
        authAppRepository = AuthAppRepository(application)
        userLiveData = authAppRepository.userLiveData
        loggedOutLiveData = authAppRepository.loggedOutLiveData
    }
}