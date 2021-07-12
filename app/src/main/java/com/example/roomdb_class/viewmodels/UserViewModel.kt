package com.example.roomdb_class.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdb_class.data.UserDatabase
import com.example.roomdb_class.domain.UserRepo
import com.example.roomdb_class.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val listUsers: LiveData<List<User>>
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

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllUsers()
        }
    }
}