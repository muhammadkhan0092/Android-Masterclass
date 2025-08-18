package com.example.androidmasterclass.modules.room_db.one_to_one.domain.use_case

import com.example.androidmasterclass.modules.room_db.one_to_one.data.repository.OneToOneRepository
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.domain.models.DataUser
import javax.inject.Inject

class OneToOneUseCase @Inject constructor(private val oneToOneRepository: OneToOneRepository) {
    fun returnUsersWithAddress() = oneToOneRepository.getUsersWithAddress()
    suspend fun insertUserWithAddress(dataUser: DataUser, dataAddress: DataAddress) = oneToOneRepository.insertUserWithAddress(dataUser,dataAddress)
}