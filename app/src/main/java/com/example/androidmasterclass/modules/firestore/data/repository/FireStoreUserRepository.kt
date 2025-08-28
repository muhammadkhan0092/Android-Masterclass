package com.example.androidmasterclass.modules.firestore.data.repository

import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.modules.firestore.data.constants.Constants.masterclass_user
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import com.example.androidmasterclass.common.firebase.data.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FireStoreUserRepository @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : UserRepository {
    override suspend fun insertUser(user: DataUser) : Resource<Unit> {
        return try {
            firebaseFirestore.collection(masterclass_user).document(user.email).set(user).await()
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message?: "Unknown Error")
        }
    }

    override fun getUsers(): Flow<Resource<List<DataUser>>> = callbackFlow {
        val subscription = firebaseFirestore
            .collection(masterclass_user)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Unknown Firestore Error"))
                    return@addSnapshotListener
                }
                val data = snapshot?.toObjects(DataUser::class.java)
                if(data!=null){
                    trySend(Resource.Success(data))
                }
                else
                {
                    trySend(Resource.Error("Data Not Available"))
                }
            }
        awaitClose { subscription.remove() }
    }

    override suspend fun deleteUser(email: String): Resource<Unit> {
        return try {
            firebaseFirestore.collection(masterclass_user).document(email).delete().await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun updateUser(user: DataUser): Resource<Unit> {
        return try {
            firebaseFirestore.collection(masterclass_user).document(user.email).set(user).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }

}