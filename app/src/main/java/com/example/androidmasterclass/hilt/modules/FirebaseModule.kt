package com.example.androidmasterclass.hilt.modules

import com.example.androidmasterclass.modules.firestore.data.repository.FireStoreUserRepository
import com.example.androidmasterclass.modules.firestore.domain.repository.UserRepository
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

    @Provides
    @Singleton
    fun providesUserRepository(fireStoreUserRepository: FireStoreUserRepository) : UserRepository{
        return fireStoreUserRepository
    }
}