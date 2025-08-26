package com.example.androidmasterclass.modules.firestore.domain.repository

import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user : DataUser) : Resource<Unit>
    fun getUsers() : Flow<Resource<List<DataUser>>>
    suspend fun deleteUser(email : String): Resource<Unit>
    suspend fun updateUser(user: DataUser): Resource<Unit>
}