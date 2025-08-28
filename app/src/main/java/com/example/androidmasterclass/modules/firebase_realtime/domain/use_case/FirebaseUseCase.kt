package com.example.androidmasterclass.modules.firebase_realtime.domain.use_case

import com.example.androidmasterclass.RealtimeRepo
import com.example.androidmasterclass.modules.firebase_realtime.data.repository.FirebaseUserRepository
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import com.example.androidmasterclass.modules.firestore.domain.repository.UserRepository
import javax.inject.Inject

class FirebaseUseCase @Inject constructor(@RealtimeRepo val firebaseUserRepository: UserRepository) {
    suspend fun insertData(user: DataUser) = firebaseUserRepository.insertUser(user)
    suspend fun updateUser(user: DataUser) = firebaseUserRepository.updateUser(user)
    suspend fun deleteUser(email: String) = firebaseUserRepository.deleteUser(email)
    fun getUsers() = firebaseUserRepository.getUsers()
}