package com.vinilo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinilo.model.User
import com.vinilo.repository.UserRepository

class MainViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val repository = UserRepository()

    fun loadUser() {
        val fetchedUser = repository.getUser()
        _user.value = fetchedUser
    }
}