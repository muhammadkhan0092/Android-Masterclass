package com.example.androidmasterclass.modules.firestore.domain.use_case

import com.example.androidmasterclass.modules.firestore.data.repository.FirebaseUserRepository
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import javax.inject.Inject

class FirestoreUseCase @Inject constructor(private val firebaseUserRepository: FirebaseUserRepository) {
    suspend fun insertData(user: DataUser) = firebaseUserRepository.insertUser(user)
    suspend fun updateUser(user: DataUser) = firebaseUserRepository.updateUser(user)
    suspend fun deleteUser(email: String) = firebaseUserRepository.deleteUser(email)
    fun getUsers() = firebaseUserRepository.getUsers()
}