package com.example.androidmasterclass.modules.firebase_realtime.data.repository

import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser
import com.example.androidmasterclass.common.firebase.data.repository.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepository @Inject constructor(firebaseDatabase: FirebaseDatabase) : UserRepository {
    val userRef = firebaseDatabase.getReference("users")
    override suspend fun insertUser(user: DataUser): Resource<Unit> {
        return try {
            userRef.child(user.email.replace('.','_')).setValue(user).await()
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message?:"Unknown Error")
        }
    }

    override fun getUsers(): Flow<Resource<List<DataUser>>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children.mapNotNull { it.getValue(DataUser::class.java) }
               trySend(Resource.Success(data))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
            }
        }
        userRef.addValueEventListener(listener)
        awaitClose{ userRef.removeEventListener(listener)}
    }

    override suspend fun deleteUser(email: String): Resource<Unit> {
        return try {
            userRef.child(email.replace('.','_')).removeValue().await()
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message?:"Unknown Error Deleting User")
        }
    }

    override suspend fun updateUser(user: DataUser): Resource<Unit> {
        return try {
            userRef.child(user.email.replace('.','_')).setValue(user).await()
            Resource.Success(Unit)
        }
        catch (e : Exception){
            Resource.Error(e.message?:"Unknown Error Updating User")
        }
    }
}