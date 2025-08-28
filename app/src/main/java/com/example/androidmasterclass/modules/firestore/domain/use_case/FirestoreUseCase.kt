package com.example.androidmasterclass.modules.firestore.domain.use_case

import com.example.androidmasterclass.FirestoreRepo
import com.example.androidmasterclass.modules.firestore.data.repository.FireStoreUserRepository
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import javax.inject.Inject

class FirestoreUseCase @Inject constructor(@FirestoreRepo private val fireStoreUserRepository: FireStoreUserRepository) {
    suspend fun insertData(user: DataUser) = fireStoreUserRepository.insertUser(user)
    suspend fun updateUser(user: DataUser) = fireStoreUserRepository.updateUser(user)
    suspend fun deleteUser(email: String) = fireStoreUserRepository.deleteUser(email)
    fun getUsers() = fireStoreUserRepository.getUsers()
}