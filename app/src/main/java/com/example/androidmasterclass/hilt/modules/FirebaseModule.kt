package com.example.androidmasterclass.hilt.modules

import com.example.androidmasterclass.modules.api_integration.data.api.ProductsApi
import com.example.androidmasterclass.modules.api_integration.data.constants.Constants.PRODUCT_BASE_URL
import com.example.androidmasterclass.modules.firestore.data.repository.FirebaseUserRepository
import com.example.androidmasterclass.modules.firestore.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providesUserRepository(firebaseUserRepository: FirebaseUserRepository) : UserRepository{
        return firebaseUserRepository
    }
}