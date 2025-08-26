package com.example.androidmasterclass.modules.firestore.presentation.view_models

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import com.example.androidmasterclass.modules.firestore.domain.use_case.FirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(private val firestoreUseCase: FirestoreUseCase) : ViewModel(){
    fun getUsers() = firestoreUseCase.getUsers()
    suspend fun insertUser(user: DataUser) = firestoreUseCase.insertData(user)
    suspend fun updateUser(user: DataUser) = firestoreUseCase.updateUser(user)
}