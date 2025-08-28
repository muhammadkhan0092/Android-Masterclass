package com.example.androidmasterclass.modules.firebase_realtime.presentation.view_models

import androidx.lifecycle.ViewModel
import com.example.androidmasterclass.modules.firebase_realtime.domain.use_case.FirebaseUseCase
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(val firebaseUseCase: FirebaseUseCase) : ViewModel() {
    fun getUsers() = firebaseUseCase.getUsers()
    suspend fun insertUser(user: DataUser) = firebaseUseCase.insertData(user)
    suspend fun updateUser(user: DataUser) = firebaseUseCase.updateUser(user)
    suspend fun deleteUser(email: String) = firebaseUseCase.deleteUser(email)
}