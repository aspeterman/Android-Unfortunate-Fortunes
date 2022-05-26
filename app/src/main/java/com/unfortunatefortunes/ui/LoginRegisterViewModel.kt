package com.unfortunatefortunes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.unfortunatefortunes.repository.AuthAppRepository


class LoginRegisterViewModel(application: Application) :
    AndroidViewModel(application) {
    private val authAppRepository: AuthAppRepository
    val userLiveData: MutableLiveData<FirebaseUser?>

    fun login(email: String?, password: String?) {
        authAppRepository.login(email, password)
    }

    fun register(email: String?, password: String?) {
        authAppRepository.register(email, password)
    }

    init {
        authAppRepository = AuthAppRepository(application)
        userLiveData = authAppRepository.userLiveData
    }
}