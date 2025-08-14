package com.example.androidmasterclass.hilt.modules

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//Module is What provides our apps with the dependencies which cannot be injected using @inject
//used for pre defined classes or classes with builder pattern
@Module
//SingletonComponent means that this module will be single instance throughout our application
@InstallIn(SingletonComponent::class)
object DataStoreModule {
}