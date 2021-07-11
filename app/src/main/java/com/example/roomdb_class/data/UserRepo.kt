package com.example.roomdb_class.data

import androidx.lifecycle.LiveData

class UserRepo (private val userDao: UserDao) {
    val listUsers: LiveData<List<User>> = userDao.listUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}