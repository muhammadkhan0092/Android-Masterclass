package com.example.androidmasterclass.common.firebase.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirestoreRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealtimeRepo
