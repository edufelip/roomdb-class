package com.example.roomdb_class.domain

import androidx.lifecycle.LiveData
import com.example.roomdb_class.data.UserDao
import com.example.roomdb_class.models.User

class UserRepo (private val userDao: UserDao) {
    val listUsers: LiveData<List<User>> = userDao.listUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}