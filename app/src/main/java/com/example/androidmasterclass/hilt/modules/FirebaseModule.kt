package com.example.androidmasterclass.hilt.modules

import com.example.androidmasterclass.common.firebase.data.di.FirestoreRepo
import com.example.androidmasterclass.common.firebase.data.di.RealtimeRepo
import com.example.androidmasterclass.modules.firebase_realtime.data.repository.FirebaseUserRepository
import com.example.androidmasterclass.modules.firestore.data.repository.FireStoreUserRepository
import com.example.androidmasterclass.common.firebase.data.repository.UserRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun providesFirebaseFirestore() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @FirestoreRepo
    @Provides
    @Singleton
    fun providesUserRepositoryFirestore(fireStoreUserRepository: FireStoreUserRepository) : UserRepository{
        return fireStoreUserRepository
    }

    @Provides
    @Singleton
    fun providesFirebaseRealtime() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @RealtimeRepo
    @Provides
    @Singleton
    fun providesUserRepositoryFirebaseRealtime(firebaseUserRepository: FirebaseUserRepository) : UserRepository{
        return firebaseUserRepository
    }

}