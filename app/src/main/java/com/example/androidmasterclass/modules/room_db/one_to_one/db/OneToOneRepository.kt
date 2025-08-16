package com.example.androidmasterclass.modules.room_db.one_to_one.db

import androidx.room.withTransaction
import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.modules.room_db.db.CustomRoomDatabase
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataAddress
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUser
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUserWithAddress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class OneToOneRepository @Inject constructor(private val oneToOneDatabase: CustomRoomDatabase) {
    private val oneToOneDao = oneToOneDatabase.getOneToOneDao()
    fun getUsersWithAddress(): Flow<Resource<List<DataUserWithAddress>>> {
        return oneToOneDao.getUserWithAddressesFlow()
            .map<List<DataUserWithAddress>, Resource<List<DataUserWithAddress>>> { list ->
                Resource.Success(list)
            }
            .onStart {
                emit(Resource.Loading())
            }
            .catch { e ->
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
    }

    suspend fun insertUserWithAddress(dataUser: DataUser, dataAddress: DataAddress): Resource<Unit> {
        return try {
            oneToOneDatabase.withTransaction {
                val userId = oneToOneDao.insertDataUser(dataUser)
                oneToOneDao.insertDataAddress(dataAddress.copy(userOwnerId = userId))
                Resource.Success(Unit)
            }
        } catch (e: Exception) {
            Resource.Error(e.message?:"Unknown Error")
        }
    }



}