package com.unfortunatefortunes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.firebase.ui.auth.data.model.User.getUser
import com.unfortunatefortunes.api.apiFortune
import com.unfortunatefortunes.api.apiUser
import com.unfortunatefortunes.model.Fortune
import com.unfortunatefortunes.model.User
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val _userProfile = MutableLiveData<User>()
    val userProfile: LiveData<User> = _userProfile
    private val _posts = MutableLiveData<List<Fortune>>()
    val posts: LiveData<List<Fortune>> = _posts

//    init {
//        viewModelScope.launch {
//            _userProfile.value = apiUser.getUsers()
//            _posts.value = apiFortune.getFortunes()
//        }
//    }
    //Rest of your viewmodel
}
