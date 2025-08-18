package com.example.androidmasterclass.modules.room_db.one_to_one.presentation.view_models

import androidx.lifecycle.ViewModel
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataUser
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.use_case.OneToOneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OneToOneViewModel @Inject constructor(private val oneToOneUseCase: OneToOneUseCase) : ViewModel() {
    suspend fun insertUserWithAddress(dataUser: DataUser, dataAddress: DataAddress) = oneToOneUseCase.insertUserWithAddress(dataUser,dataAddress)
    fun getUsersWithAddress() = oneToOneUseCase.returnUsersWithAddress()
}