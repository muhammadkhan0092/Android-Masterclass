package com.example.androidmasterclass.modules.room_db.one_to_one.view_models

import androidx.lifecycle.ViewModel
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OneToOneViewModel @Inject constructor(private val oneToOneUseCase: OneToOneUseCase) : ViewModel() {
    suspend fun insertUserWithAddress(dataUser: DataUser,dataAddress: DataAddress) = oneToOneUseCase.insertUserWithAddress(dataUser,dataAddress)
    fun getUsersWithAddress() = oneToOneUseCase.returnUsersWithAddress()
}