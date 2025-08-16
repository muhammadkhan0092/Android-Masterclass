package com.example.androidmasterclass.modules.room_db.one_to_one.view_models

import com.example.androidmasterclass.modules.room_db.one_to_one.db.OneToOneRepository
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUser
import javax.inject.Inject

class OneToOneUseCase @Inject constructor(private val oneToOneRepository: OneToOneRepository) {
    fun returnUsersWithAddress() = oneToOneRepository.getUsersWithAddress()
    suspend fun insertUserWithAddress(dataUser: DataUser, dataAddress: DataAddress) = oneToOneRepository.insertUserWithAddress(dataUser,dataAddress)
}