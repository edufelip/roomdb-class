package com.example.roomdb_class.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val listUsers: LiveData<List<User>>
    private val repo: UserRepo

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repo = UserRepo(userDao)
        listUsers = repo.listUsers
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }
}